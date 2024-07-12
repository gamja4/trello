package com.gamja.trello.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamja.trello.common.response.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.FORBIDDEN;
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        CommonResponse.builder()
                                .status(status.value())
                                .msg("로그인하지 않았거나 토큰이 만료되었습니다.")
                                .build()
                                .toResponseEntity()
                )
        );
    }
}