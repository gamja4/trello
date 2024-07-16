package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class BoardReadResponseDto {
    private Long boardId;
    private String title;

    private Page<Section> sections;
    private Page<Card> cards;

    public BoardReadResponseDto(Board board, Page<Section> sections, Page<Card> cards) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.sections = sections;
        this.cards = cards;
    }
}
