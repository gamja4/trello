package com.gamja.trello.config;

import com.gamja.trello.repository.BoardCustomRepository;
import com.gamja.trello.repository.impl.BoardCustomRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestRepositoryConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public BoardCustomRepository boardCustomRepository() {
        return new BoardCustomRepositoryImpl(jpaQueryFactory());
    }
}