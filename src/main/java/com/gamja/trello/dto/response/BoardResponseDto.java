package com.gamja.trello.dto.response;


import com.gamja.trello.entity.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardResponseDto {

    private final String title;

    @Builder
    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
    }

}
