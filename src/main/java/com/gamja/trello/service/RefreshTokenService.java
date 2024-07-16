package com.gamja.trello.service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.RefreshToken;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken saveAndUpdateToken(String refreshToken, User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId()).orElse(
                RefreshToken.builder()
                        .token(refreshToken)
                        .user(user)
                        .build()
        );

        token.updateRefreshToken(refreshToken);

        return refreshTokenRepository.save(token);
    }

    public void removeToken(Long userId) {
        RefreshToken token = findByUserId(userId);
        if (token == null) {
            return;
        }
        refreshTokenRepository.delete(token);
    }

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token).orElseThrow(
                () -> new CustomException(ErrorCode.TOKEN_NOT_FOUND)
        );
    }

    public RefreshToken findByUserId(Long userId) {
        return refreshTokenRepository.findByUserId(userId).orElse(null);
    }
}
