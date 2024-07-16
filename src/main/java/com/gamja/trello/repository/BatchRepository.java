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
        int size = 100000;
        jdbcTemplate.batchUpdate(
                "insert into board(id, title)" +
                        "values(?,?)",
        new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, i + startId);
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

    public void sectionBatchInsert(int startId, int size) {
        System.out.println(startId + "번째 부터 insert를 시작합니다.");
        jdbcTemplate.batchUpdate(
                "insert into section(id, title, sort, board_id)" +
                        "values(?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, i + startId);
                        ps.setString(2, "title1");
                        ps.setInt(3, 0);
                        ps.setLong(4, 1L);
                    }

                    @Override
                    public int getBatchSize() {
                        return size;
                    }
                }
        );

        System.out.println(startId + "번째 부터 insert가 완료되었습니다.");
    }

    public void cardBatchInsert(int startId, int size) {
        System.out.println(startId + "번째 부터 insert를 시작합니다.");
        jdbcTemplate.batchUpdate(
                "insert into card(id, content, title, sort, status, writer, section_id, user_id, due_date)" +
                        "values(?, ?, ?, ?, ? ,? ,?, ? ,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, i + startId);
                        ps.setString(2, "content" + startId + i);
                        ps.setString(3, "title1" + + startId + i);
                        ps.setInt(4, 0);
                        ps.setInt(5, 0);
                        ps.setString(6, "test1");
                        ps.setLong(7, i+1);   // section_id
                        ps.setLong(8, 1);
                        ps.setString(9, "2024-07-13");
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