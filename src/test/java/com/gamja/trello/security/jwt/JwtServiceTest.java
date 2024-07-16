package com.gamja.trello.security.jwt;

import com.gamja.trello.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {

    @Autowired
    JwtService jwtService;

    @DisplayName("토큰을 발급한다.")
    @Test
     void createAccessToken() {
        // given
        String email = "test1@test.com";
        User.Role role = User.Role.USER;

        // when
        String token = jwtService.createAccessToken(email, role);

        // then
        System.out.println(token);
    }

    @DisplayName("토큰으로부터 유저 정보를 가져온다")
    @Test
    void getUserInfoFromToken() {
        // given
        String inputEmail = "test1@test.com";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0MUB0ZXN0LmNvbSIsImF1dGgiOiJVU0VSIiwiZXhwIjoxNzIwNjE0ODk4LCJpYXQiOjE3MjA2MTMwOTh9.gYWI-mE5w7b-9s7Dc8IF0hTBaWlWkwEiQYqI7mYYNcS1pFstl1APC33tWzvvynp2fny31h7CzGWMgGhrPpuzMA";

        // when
         String email = jwtService.getEmailFromToken(token);

        // then
        assertEquals(email, inputEmail);
    }

}