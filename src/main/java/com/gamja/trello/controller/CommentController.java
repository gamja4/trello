package com.gamja.trello.controller;

import com.gamja.trello.dto.request.CommentRequestDto;
import com.gamja.trello.dto.response.CommentResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards/{boardId}/sections/{sectionId}/cards/{cardId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@PathVariable("cardId") Long cardId,
                                           @Valid @RequestBody CommentRequestDto req,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(cardId, req, userDetails.getUser()));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable("cardId") Long cardId) {
        return ResponseEntity.ok(commentService.getComments(cardId));
    }
}
