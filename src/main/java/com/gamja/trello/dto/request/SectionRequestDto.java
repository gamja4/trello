package com.gamja.trello.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class SectionRequestDto {
	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;
}
