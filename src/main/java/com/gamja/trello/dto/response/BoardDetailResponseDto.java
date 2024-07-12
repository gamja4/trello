package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Section;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardDetailResponseDto {

    private final Long id;
    private final String title;
    private final List<SectionDetailResponseDto> sections;

    @Builder
    public BoardDetailResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.sections = board.getSections().stream().map(SectionDetailResponseDto::new).toList();
    }

    @Getter
    public static class SectionDetailResponseDto {

        private final Long id;
        private final String title;
        private final List<CardResponseDto> cards;

        @Builder
        public SectionDetailResponseDto(Section section) {
            this.id = section.getId();
            this.title = section.getTitle();
            this.cards = section.getCards().stream().map(CardResponseDto::new).toList();
        }
    }


}
