package com.gamja.trello.repository;

import com.gamja.trello.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
//    @Query(value = "select b " +
//            "from Board b " +
//            "       left join fetch b.sections s " +
//            "       left join fetch s.cards c " +
//            "where b.id= :id " +
//            "order by s.sort ")
//    Optional<Board> findBoardAllById(@Param("id") Long id, Pageable pageable);

//    @Query(value = "select " +
//            "    b.id, b.title, " +
//            "    s.id, s.title, s.board_id, " +
//            "    c.id, c.section_id, c.title, c.content, c.sort, " +
//            "    c.due_date, c.status, c.writer, c.created_at, c.modified_at " +
//            "from board b " +
//            "         left join section s on b.id = s.board_id " +
//            "         left join card c on c.section_id = s.id " +
//            "where b.id = 1 " +
//            "order by sort" +
//            "limit 100", nativeQuery = true)
//    Optional<List<BoardDetailsDto>> findBoardAllByBoardId(@Param("boardId") Long boardId);
}
