package com.gamja.trello.service;

import com.gamja.trello.dto.request.CommentRequestDto;
import com.gamja.trello.dto.response.CommentResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Comment;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CardService cardService;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long cardId, CommentRequestDto req, User user) {
        Card card = cardService.findCardById(cardId);
        Comment comment = Comment.builder()
                .content(req.getContent())
                .card(card)
                .user(user)
                .build();

        return new CommentResponseDto(commentRepository.save(comment));
    }

    public List<CommentResponseDto> getComments(Long cardId) {
        List<Comment> comments = commentRepository.findAllByCardId(cardId);
        return comments.stream().map(CommentResponseDto::new).toList();
    }
}
