package com.gamja.trello.service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.SignupRequestDto;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        User user = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .nickname(req.getNickname())
                .role(User.Role.USER)
                .build();

        userRepository.save(user);
    }
}
