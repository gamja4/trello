package com.gamja.trello.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_id", unique = true)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public RefreshToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public void updateRefreshToken(String token) {
        this.token = token;
    }
}
