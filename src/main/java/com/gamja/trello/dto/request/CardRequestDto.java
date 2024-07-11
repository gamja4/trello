package com.gamja.trello.dto.request;

import lombok.Getter;

@Getter
public class CardRequestDto {
    private Long id;
    private String title;
    private String content;
}