package com.gamja.trello.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class MoveCardRequestDto {

    List<card> cards;

    @Getter
    public static class card {
        private Long id;
        private int sort;
    }
}
