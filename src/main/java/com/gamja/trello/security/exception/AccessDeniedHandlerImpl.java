package com.gamja.trello.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamja.trello.common.response.CommonResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpStatus status = HttpStatus.FORBIDDEN;
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        CommonResponse.builder()
                                .status(status.value())
                                .msg("인증된 유저만 접근 가능합니다.")
                                .build()
                                .toResponseEntity()
                )
        );
    }
}