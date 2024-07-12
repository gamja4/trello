package com.gamja.trello.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
    private String content;
}
