package com.gamja.trello.dto.response;

import java.time.LocalDateTime;

import com.gamja.trello.entity.Section;

public class SectionResponseDto {
	private final Long id;
	private final String title;
	private final LocalDateTime createdAt;
	private final LocalDateTime modifiedAt;

	public SectionResponseDto(Section section) {
		this.id = section.getId();
		this.title = section.getTitle();
		this.createdAt = section.getCreatedAt();
		this.modifiedAt = section.getModifiedAt();
	}
}
