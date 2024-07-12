package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Section;
import lombok.Getter;

import java.util.List;

@Getter
public class MoveSectionResponseDto {
    List<SectionResponseDto> sections;

    public MoveSectionResponseDto(List<Section> sections) {
        this.sections = sections.stream().map(SectionResponseDto::new).toList();
    }
}
