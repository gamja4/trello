package com.gamja.trello.controller;

import com.gamja.trello.dto.request.BoardRequestDto;
import com.gamja.trello.dto.request.InviteRequestDto;
import com.gamja.trello.dto.response.BoardDetailResponseDto;
import com.gamja.trello.dto.response.BoardResponseDto;
import com.gamja.trello.dto.response.InviteResponseDto;
import com.gamja.trello.dto.response.UserResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.BoardService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoards() {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoardList());

    }

    @GetMapping("/user")
    public ResponseEntity<List<BoardResponseDto>> getUserBoards(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getUserBoardList(userDetails.getUser()));

    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDetailResponseDto> getBoard(@PathVariable Long boardId) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoard(boardId));

    }

    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody BoardRequestDto req,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.postBoard(req, userDetails.getUser()));

    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> putBoard(@PathVariable Long boardId,
                                      @RequestBody BoardRequestDto req,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.putBoard(boardId, req, userDetails.getUser()));

    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(boardService.deleteBoard(boardId, userDetails.getUser()));
    }

    @PostMapping("/{boardId}/invite")
    public ResponseEntity<List<InviteResponseDto>> inviteBoard(@PathVariable Long boardId,
                                                         @RequestBody List<InviteRequestDto> req,
                                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return ResponseEntity.status(HttpStatus.OK).body(boardService.inviteBoard(boardId, req, userDetails.getUser()));

    }

}
