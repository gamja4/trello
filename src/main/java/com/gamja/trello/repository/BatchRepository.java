package com.gamja.trello.repository;

import com.gamja.trello.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class BatchRepository {
    private final JdbcTemplate jdbcTemplate;

    public void boardBatchInsert(int startId) {
        System.out.println(startId + "번째 부터 insert를 시작합니다.");
        int finalStartId = startId++;
        int size = 100000;
        jdbcTemplate.batchUpdate(
                "insert into board(id, title)" +
                        "values(?,?)",
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, i + finalStartId);
                ps.setString(2, "title1");
            }

            @Override
            public int getBatchSize() {
                return size;
            }
        }
        );

        System.out.println(startId + "번째 부터 insert가 완료되었습니다.");
    }
}
