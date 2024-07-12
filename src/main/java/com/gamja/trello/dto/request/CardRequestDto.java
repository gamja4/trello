package com.gamja.trello.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CardRequestDto {
    @NotBlank
    private String title;

    private String content;

    private LocalDate dueDate;

    @NotBlank
    private String status;

    private String writer;
}