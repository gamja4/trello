package com.gamja.trello.dto;

import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private Page<SectionDto> sections;
    private PageDto cardPage;

    public BoardDto(Board board, Page<SectionDto> sections, Page<CardDto> cards) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.sections = sections;
        this.cardPage = new PageDto(cards.getTotalPages(), cards.hasNext(), cards.isLast());

        // 각 카드를 해당 섹션에 추가
        for (CardDto card : cards) {
            for (SectionDto section : this.sections.getContent()) {
                if (section.getId().equals(card.getSectionId())) {
                    section.addCard((card));
                }
            }
        }
    }

    @Getter
    @AllArgsConstructor
    public static class SectionDto {
        private Long id;
        private String title;
        private List<CardDto> cards = new ArrayList<>();

        public SectionDto(Long id, String title) {
            this.id = id;
            this.title = title;
        }

        public void addCard(CardDto card) {
            this.cards.add(card);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class CardDto {
        private Long id;
        private Long sectionId;
        private String title;
        private String content;
        private int sort;
        private LocalDate dueDate;
        private String status;
        private String writer;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @AllArgsConstructor
    public static class PageDto {
        private int totalPages;
        private boolean hasNext;
        private boolean isLast;
    }
}
