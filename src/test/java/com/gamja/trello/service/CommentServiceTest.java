package com.gamja.trello.service;

import com.gamja.trello.dto.request.CommentRequestDto;
import com.gamja.trello.dto.response.CommentResponseDto;
import com.gamja.trello.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CommentServiceTest {
    User user;
    Card card;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    CommentService commentService;

    @BeforeEach
    void setUp() {
        testDataInit();
    }

    @DisplayName("댓글을 작성한다.")
    @Test
    void createComment() {
        // given
        Long cardId = card.getId();
        String content = "content";
        CommentRequestDto req = new CommentRequestDto(content);

        // when
        CommentResponseDto res = commentService.createComment(cardId, req, user);

        // then
        assertEquals(res.getContent(), content);
    }

    @DisplayName("댓글을 조회한다")
    @Test
    void getComments() {
        // given
        Long cardId = card.getId();

        // when
        List<CommentResponseDto> req = commentService.getComments(cardId);

        // then
        assertEquals(req.size(), 2);
    }

    void testDataInit() {
        user = User.builder()
                .email("test2@test.com")
                .password("password")
                .nickname("test2")
                .role(User.Role.USER)
                .build();

        card = Card.builder()
                .title("title")
                .content("card")
                .build();

        entityManager.persist(user);
        entityManager.persist(card);
        entityManager.flush();

        Comment comment1 = Comment.builder()
                .content("comment")
                .card(card)
                .user(user)
                .build();

        Comment comment2 = Comment.builder()
                .content("comment2")
                .card(card)
                .user(user)
                .build();

        entityManager.persist(comment1);
        entityManager.persist(comment2);

        entityManager.flush();
    }
}