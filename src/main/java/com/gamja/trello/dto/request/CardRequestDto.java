package com.gamja.trello.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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