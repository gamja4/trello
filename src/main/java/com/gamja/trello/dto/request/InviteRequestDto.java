package com.gamja.trello.dto.request;

import com.gamja.trello.entity.BoardInvitation;
import lombok.Getter;

@Getter
public class InviteRequestDto {
    private String email;
    private BoardInvitation.BoardRole role;
}
