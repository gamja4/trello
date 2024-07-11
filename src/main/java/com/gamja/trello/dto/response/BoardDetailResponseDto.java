package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BoardDetailResponseDto {

    private final String title;
    private final List<SectionDetailResponseDto> sections;

    @Builder
    public BoardDetailResponseDto(Board board) {
        this.title = board.getTitle();
        this.sections = board.getSections().stream().map(SectionDetailResponseDto::new).toList();
    }

    @Getter
    public static class SectionDetailResponseDto {
        private final String title;
        private final List<CardResponseDto> cards;

        @Builder
        public SectionDetailResponseDto(Section section) {
            this.title = section.getTitle();
            this.cards = section.getCards().stream().map(CardResponseDto::new).toList();
        }
    }


}
