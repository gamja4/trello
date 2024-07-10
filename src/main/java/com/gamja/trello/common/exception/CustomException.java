package com.gamja.trello.common.exception;

public class CustomException extends RuntimeException{
    ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
}
