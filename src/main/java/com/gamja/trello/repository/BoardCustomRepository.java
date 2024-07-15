package com.gamja.trello.repository;

import com.gamja.trello.dto.BoardDto;
import com.gamja.trello.dto.response.BoardReadResponseDto;
import com.gamja.trello.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCustomRepository {
    Page<Board> getFetchJoinBoard(Long id, int page, int size);
    BoardDto getFetchJoinBoard(Long id, Pageable pageable);
}
