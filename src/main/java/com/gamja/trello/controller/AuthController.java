package com.gamja.trello.controller;

import com.gamja.trello.dto.request.LoginRequestDto;
import com.gamja.trello.dto.request.ReissueTokenRequestDto;
import com.gamja.trello.dto.response.TokenResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto req) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(req));
    }

    @PostMapping("/logout")
    public  ResponseEntity<?> logout(@AuthenticationPrincipal UserDetailsImpl userDetails){
        authService.logout(userDetails.getUser());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/token/reissue")
    public ResponseEntity<TokenResponseDto> reissueToken(@RequestBody ReissueTokenRequestDto req) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.reissueToken(req));
    }

}
