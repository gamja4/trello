package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private String content;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.content = comment.getContent();
        this.modifiedAt = comment.getModifiedAt();
    }
}
