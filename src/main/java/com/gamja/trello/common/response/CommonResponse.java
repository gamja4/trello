package com.gamja.trello.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class CommonResponse {
    private int status;
    private String msg;
    private Object data;

    @Builder
    public CommonResponse(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntity<?> toResponseEntity() {
        return ResponseEntity
                .status(status)
                .body(this);
    }
}
