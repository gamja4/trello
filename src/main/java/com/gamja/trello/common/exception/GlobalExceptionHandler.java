package com.gamja.trello.common.exception;

import com.gamja.trello.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        return CommonResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(ErrorCode.FAIL.getMsg())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandler(CustomException e) {
        return CommonResponse.builder()
                .status(e.errorCode.getHttpStatus().value())
                .msg(e.errorCode.getMsg())
                .build()
                .toResponseEntity();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return String.format("[%s]: %s", fieldError.getField(), fieldError.getDefaultMessage());
                }).collect(Collectors.joining("\n"));
        return CommonResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .msg(errorMsg)
                .build()
                .toResponseEntity();
    }
}
