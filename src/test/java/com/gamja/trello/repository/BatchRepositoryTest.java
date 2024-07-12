package com.gamja.trello.repository;

import com.gamja.trello.config.FixtureMonkeyUtil;
import com.gamja.trello.entity.Board;
import com.gamja.trello.helper.ConcurrencyHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@SpringBootTest
class BatchRepositoryTest {
    @Autowired
    BatchRepository batchRepository;

    @DisplayName("board 더미 데이터 생성 > 10만건 생성시 12분 소요")
    @Rollback(value = false)
    @Test
    void batchInsert() {
        int id = 339995;
        System.out.println("===== start =====");
        batchRepository.boardBatchInsert(id);
        System.out.println("===============================================================================================");


    }

    @DisplayName("board 더미 데이터 생성 > 병렬 처리")
    @Rollback(value = false)
    @Test
    void batchInsert2() throws InterruptedException {

        int tableId = 2208158;

        ConcurrencyHelper.execute(
                (id) -> batchRepository.boardBatchInsert(id),
                tableId
        );
        //

        System.out.println("ㅁㄴㅇㄹ");

    }
}