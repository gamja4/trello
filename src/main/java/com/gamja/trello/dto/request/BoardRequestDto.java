package com.gamja.trello.dto.request;

import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
public class BoardRequestDto {
    private String title;
}
