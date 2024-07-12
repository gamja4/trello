package com.gamja.trello.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InviteResponseDto {
    private String email;

    @Builder
    public InviteResponseDto(String email) {
        this.email = email;
    }
}
