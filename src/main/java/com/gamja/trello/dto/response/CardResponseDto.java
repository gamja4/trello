package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Card;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardResponseDto {

    private Long id;
    private String title;
    private String content;
    private int sort;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CardResponseDto(Card card){
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.sort = card.getSort();
        this.createdAt = card.getCreatedAt();
        this.modifiedAt = card.getModifiedAt();
    }


}
