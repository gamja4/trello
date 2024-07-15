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

    public BoardDto(Board board, Page<SectionDto> sections, Page<CardDto> cards) {
        this.id = board.getId();
        this.title = board.getTitle();

        // sections 리스트 초기화
        this.sections = sections;

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

        public Long getId() {
            return id;
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

        public CardDto(Card card) {
            this.id = card.getId();
            this.sectionId = card.getSection().getId();
            this.title = card.getTitle();
            this.content = card.getContent();
            this.sort = card.getSort();
            this.dueDate = card.getDueDate();
            this.status = card.getStatus();
            this.writer = card.getWriter();
            this.createdAt = card.getCreatedAt();
            this.modifiedAt = card.getModifiedAt();
        }
    }
}
