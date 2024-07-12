package com.gamja.trello.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "실패하였습니다."),

    //User
    USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다."),
    USER_INACTIVITY(HttpStatus.FORBIDDEN, "탈퇴된 사용자입니다."),

    //Board
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "보드를 찾을 수 없습니다."),
    UNAUTHORIZED_BOARD(HttpStatus.UNAUTHORIZED, "보드를 수정할 권한이 없습니다."),

    //Section
    SECTION_NOT_FOUND(HttpStatus.NOT_FOUND, "섹션을 찾을 수 없습니다."),
    UNAUTHORIZED_SECTION(HttpStatus.UNAUTHORIZED, "섹션을 수정할 권한이 없습니다."),

    // Card
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "카드를 찾을 수 없습니다."),

    //Comment
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    UNAUTHORIZED_COMMENT(HttpStatus.UNAUTHORIZED, "댓글을 수정할 권한이 없습니다."),

    // Token
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰 없음."),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "토큰 만료됨."),
    TOKEN_INVALID(HttpStatus.BAD_REQUEST, "토큰 유효하지 않음")
    ;

    private HttpStatus httpStatus;
    private String msg;
}
