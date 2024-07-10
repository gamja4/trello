package com.gamja.trello.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "실패하였습니다.")
    ;

    private HttpStatus httpStatus;
    private String msg;
}
