package com.gamja.trello.dto.response;

import com.gamja.trello.entity.User;

public class UserResponseDto {
    private String email;
    private String nickname;
    private String role;

    public UserResponseDto(User user){
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.role = user.getRole().getRoleName();
    }
}
