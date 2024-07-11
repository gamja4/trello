package com.gamja.trello.service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.LoginRequestDto;
import com.gamja.trello.dto.request.ReissueTokenRequestDto;
import com.gamja.trello.dto.response.TokenResponseDto;
import com.gamja.trello.entity.RefreshToken;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.RefreshTokenRepository;
import com.gamja.trello.repository.UserRepository;
import com.gamja.trello.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public TokenResponseDto login(LoginRequestDto req) {
        User user = userRepository.findUserByEmail(req.getEmail());

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        user.verifyUser();

        String accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole());

        refreshTokenService.saveAndUpdateToken(refreshToken, user);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponseDto reissueToken(ReissueTokenRequestDto req) {
        RefreshToken savedToken = refreshTokenService.findByToken(req.getToken());

        User user = savedToken.getUser();

        String accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole());
        String refreshToken = jwtService.reissueToken(savedToken.getToken(), user);

        refreshTokenService.saveAndUpdateToken(refreshToken, user);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    public void logout(User user) {
        refreshTokenService.removeToken(user.getId());
    }

}
