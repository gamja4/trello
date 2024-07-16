package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Card;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MoveCardResponseDto {
    List<CardResponseDto> cards;

    public MoveCardResponseDto(List<Card> cards) {
        this.cards = cards.stream().map(CardResponseDto::new).toList();
    }
}
