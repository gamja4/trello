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

        for (Board board : boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .board(board)
                    .build();
            res.add(dto);
        }

        return res;
    }

    @Transactional
    public List<BoardResponseDto> getUserBoardList(User user) {

        List<Board> boards = findAllBoardByUser(user.getId());
        List<BoardResponseDto> res = new ArrayList<>();

        for (Board board : boards) {
            BoardResponseDto dto = BoardResponseDto.builder()
                    .board(board)
                    .build();
            res.add(dto);
        }

        return res;
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

        List<InviteResponseDto> invitations = new ArrayList<>();

        for (InviteRequestDto request : req) {
            BoardInvitation invitation = BoardInvitation.builder()
                    .board(board)
                    .user(userRepository.findUserByEmail(request.getEmail()))
                    .build();
            invitations.add(new InviteResponseDto(invitation.getId().getUser().getEmail()));
            boardInvitationRepository.save(invitation);
        }

        return invitations;
    }

    private void validateUserRole (User user, Board board) {

        if(!user.getRole().equals(User.Role.MANAGER)) {
            BoardInvitationId id = getInvId(user, board);
            if(!isExistUserBoard(id) || !getUserBoardRole(id).equals(BoardInvitation.BoardRole.OWNER)) {
                throw  new CustomException(ErrorCode.UNAUTHORIZED_BOARD);
            }
        }
    }

    private Board findBoard(Long id) {

        return boardRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
    }

    private List<Board> findAllBoard() {
        return boardRepository.findAll();
    }

    private List<Board> findAllBoardByUser(Long userId) {

       List<BoardInvitation> invitationList = boardInvitationRepository.findById_UserId(userId);
       List<Board> res = new ArrayList<>();

        for (BoardInvitation invitation : invitationList) {
            res.add(invitation.getId().getBoard());
        }

        return res;
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
