package com.gamja.trello.security.jwt;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Slf4j(topic = "JwtService")
@Component
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secret;
    private Key key;

    public static String CLAIM_ROLE = "auth";
    public static final String BEARER_PREFIX = "Bearer ";

    @Value("${jwt.time.access}")
    private Long ACCESS_TOKEN_TIME;
    @Value("${jwt.time.refresh}")
    private Long REFRESH_TOKEN_TIME;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // Access Token
    public String createAccessToken(String email, User.Role role) {
        return createToken(email, role, ACCESS_TOKEN_TIME);
    }

    // Refresh Token
    public String createRefreshToken(String email, User.Role role) {
        return createToken(email, role, REFRESH_TOKEN_TIME);
    }

    // Reissue Token
    public String reissueToken(String refreshToken, User user) {
        if(!isTokenValidate(refreshToken)) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }

        return createAccessToken(user.getEmail(), user.getRole());
    }

    // JWT
    public String createToken(String email, User.Role role, Long expirationTime) {
        Date date = new Date();

        return Jwts.builder()
                .subject(email)
                .claims()
                .add(CLAIM_ROLE, role)
                .expiration(new Date(date.getTime() + expirationTime))
                .issuedAt(date)
                .and()
                .signWith(key).compact();
    }

    public boolean isTokenValidate(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰으로 부터 유저 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String substringToken(String token) {
        return token.substring(BEARER_PREFIX.length());
    }
}
