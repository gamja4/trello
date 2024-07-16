package com.gamja.trello.repository.impl;

import com.gamja.trello.config.TestRepositoryConfig;
import com.gamja.trello.dto.BoardDto;
import com.gamja.trello.dto.response.BoardReadResponseDto;
import com.gamja.trello.entity.Board;
import com.gamja.trello.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestRepositoryConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class BoardCustomRepositoryImplTest {
    @Autowired
    BoardCustomRepositoryImpl boardCustomRepository;

    @Autowired
    BoardRepository boardRepository;

    @DisplayName("board에 있는 데이터를 페이지네이션 후 fetch join하여 가져온다.")
    @Test
    void test1() {
        // given
        Long boardId = 1L;
        int page = 1;
        int size = 1;

        // when
//        List<Board> board = boardCustomRepository.getFetchJoinBoard(boardId, page, size);
        Page<Board> board = boardCustomRepository.getFetchJoinBoard(boardId, page, size);

        System.out.println(board.getContent().get(0).getId());
        // then
        // fetch join은 limit 적용이 되지 않는다. -> oom 발생
    }

    @DisplayName("쿼리 분할")
    @Test
    void test2() {
        // given
        Long boardId = 1L;
        int page = 1;
        int size = 10;

        Pageable pageable = PageRequest.of(page-1, size);

        // when
        BoardDto board = boardCustomRepository.getFetchJoinBoard(boardId, pageable);

        // then
        System.out.println(board.getId());
    }
}