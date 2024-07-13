package com.gamja.trello.repository;

import com.gamja.trello.config.FixtureMonkeyUtil;
import com.gamja.trello.entity.Board;
import com.gamja.trello.helper.ConcurrencyHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class BatchRepositoryTest {
    @Autowired
    BatchRepository batchRepository;

    @PersistenceContext
    EntityManager entityManager;

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
    }

    @DisplayName("section 더미 데이터 생성 > Flux를 활용한 병렬 처리")
    @Rollback(value = false)
    @Test
    void batchInsert3() throws InterruptedException {

        Query curCount = entityManager.createNativeQuery("select count(*) from section;");
        Long result = ((Number)curCount.getSingleResult()).longValue();

        System.out.println("현재 데이터 삽입 갯수: "+ result);

        curCount = entityManager.createNativeQuery("select max(id) from section;");
        int tableId  = ((Number)curCount.getSingleResult()).intValue();

        System.out.println("현재 id: "+ tableId);

        int count = 10; // jdbc 기본 커넥션이 10
        int size = 10000;

        Flux.range(0, count)
                .parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(
                        data -> batchRepository.sectionBatchInsert(
                                tableId + (data * size) + 1,
                                size)
                )
                .then()
                .block();
    }
}