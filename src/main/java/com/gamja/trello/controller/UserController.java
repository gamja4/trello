package com.gamja.trello.controller;

import com.gamja.trello.dto.request.SignupRequestDto;
import com.gamja.trello.dto.response.UserResponseDto;
import com.gamja.trello.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto req) {
        userService.signup(req);
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }
}
