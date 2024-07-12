package com.gamja.trello.repository;

import com.gamja.trello.config.FixtureMonkeyUtil;
import com.gamja.trello.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
class BatchRepositoryTest {
    @Autowired
    BatchRepository batchRepository;

    @DisplayName("board 더미 데이터 생성")
    @Rollback(value = false)
    @Test
    void batchInsert() {
        System.out.println("===== start =====");
        batchRepository.boardBatchInsert();
        System.out.println("===============================================================================================");
    }
}