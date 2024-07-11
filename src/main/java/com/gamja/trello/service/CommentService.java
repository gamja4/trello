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
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(Long cardId, CommentRequestDto req, User user) {
        // TODO 존재하는 카드인지 확인
        Comment comment = Comment.builder()
                .content(req.getContent())
                .card(Card.builder()
                        .id(cardId)
                        .build())
                .user(user)
                .build();

        return new CommentResponseDto(commentRepository.save(comment));
    }

    public List<CommentResponseDto> getComments(Long cardId) {
        List<Comment> comments = commentRepository.findAllByCardId(cardId);
        return comments.stream().map(CommentResponseDto::new).toList();
    }
}
