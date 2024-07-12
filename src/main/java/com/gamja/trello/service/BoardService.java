package com.gamja.trello.service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.BoardRequestDto;
import com.gamja.trello.dto.request.InviteRequestDto;
import com.gamja.trello.dto.response.BoardDetailResponseDto;
import com.gamja.trello.dto.response.BoardResponseDto;
import com.gamja.trello.dto.response.InviteResponseDto;
import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.BoardInvitation;
import com.gamja.trello.entity.BoardInvitationId;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.BoardInvitationRepository;
import com.gamja.trello.repository.BoardRepository;
import com.gamja.trello.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardInvitationRepository boardInvitationRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<BoardResponseDto> getBoardList() {

        List<Board> boards = findAllBoard();
        List<BoardResponseDto> res = new ArrayList<>();

        return boards.stream()
                .map(board -> BoardResponseDto.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<BoardResponseDto> getUserBoardList(User user) {
        List<Board> boards = findAllBoardByUser(user.getId());

        return boards.stream()
                .map(board -> BoardResponseDto.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardDetailResponseDto getBoard(Long boardId) {

        Board board = findBoard(boardId);

        return BoardDetailResponseDto.builder()
                .board(board)
                .build();
    }

    @Transactional
    public BoardResponseDto postBoard(BoardRequestDto req, User user) {

        Board board = Board.builder()
                .title(req.getTitle())
                .build();

        BoardInvitation invitation = BoardInvitation.builder()
                .board(board)
                .user(user)
                .role(BoardInvitation.BoardRole.OWNER)
                .build();

        boardRepository.save(board);
        boardInvitationRepository.save(invitation);

        return new BoardResponseDto(boardRepository.save(board));
    }


    @Transactional
    public BoardResponseDto putBoard(Long boardId, BoardRequestDto req, User user) {

        Board board = findBoard(boardId);
        validateUserRole(user, board);

        Board newBoard = Board.builder()
                .id(board.getId())
                .title(req.getTitle())
                .build();

        return new BoardResponseDto(boardRepository.save(newBoard));

    }


    @Transactional
    public Void deleteBoard(Long boardId, User user) {

        Board board = findBoard(boardId);

        validateUserRole(user, board);

        boardRepository.delete(board);

        return null;
    }


    public List<InviteResponseDto> inviteBoard(Long boardId, List<InviteRequestDto> req, User user) {
        Board board = findBoard(boardId);
        validateUserRole(user, board);
        validateUserExistBoard(req, board);

        return req.stream()
                .map(request -> {
                    BoardInvitation invitation = BoardInvitation.builder()
                            .board(board)
                            .user(userRepository.findUserByEmail(request.getEmail()))
                            .role(request.getRole())
                            .build();
                    boardInvitationRepository.save(invitation);
                    return new InviteResponseDto(invitation.getId().getUser().getEmail());
                })
                .collect(Collectors.toList());
    }

    private void validateUserRole (User user, Board board) {

        if(!user.getRole().equals(User.Role.MANAGER)) {
            BoardInvitationId id = getInvId(user, board);
            if(!isExistUserBoard(id) || !getUserBoardRole(id).equals(BoardInvitation.BoardRole.OWNER)) {
                throw new CustomException(ErrorCode.UNAUTHORIZED_BOARD);
            }
        }
    }

    private void validateUserExistBoard(List<InviteRequestDto> req, Board board) {
        List<User> users = findAllUserByBoard(board.getId());

        req.stream()
                .map(InviteRequestDto::getEmail)
                .filter(email -> users.stream().anyMatch(user -> user.getEmail().equals(email)))
                .findAny()
                .ifPresent(email -> {
                    throw new CustomException(ErrorCode.USER_ALREADY_INVITED);
                });
    }

    public Board findBoard(Long id) {

        return boardRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
    }

    private List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    private List<Board> findAllBoardByUser(Long userId) {
        List<BoardInvitation> invitationList = boardInvitationRepository.findById_UserId(userId);

        return invitationList.stream()
                .map(invitation -> invitation.getId().getBoard())
                .collect(Collectors.toList());
    }

    private List<User> findAllUserByBoard(Long boardId) {
        List<BoardInvitation> invitationList = boardInvitationRepository.findById_BoardId(boardId);

        return invitationList.stream()
                .map(invitation -> invitation.getId().getUser())
                .collect(Collectors.toList());
    }

    private BoardInvitation.BoardRole getUserBoardRole(BoardInvitationId id) {

        BoardInvitation inv = boardInvitationRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.UNAUTHORIZED_BOARD)
        );

        return inv.getRole();
    }

    private boolean isExistUserBoard(BoardInvitationId id) {
        return boardInvitationRepository.existsById(id);
    }

    private BoardInvitationId getInvId(User user, Board board) {
        return BoardInvitationId.builder()
                .user(user)
                .board(board)
                .build();
    }
}
