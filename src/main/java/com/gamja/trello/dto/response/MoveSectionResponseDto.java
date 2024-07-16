package com.gamja.trello.dto.response;

import com.gamja.trello.entity.Section;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class MoveSectionResponseDto {
    List<SectionResponseDto> sections;

    public MoveSectionResponseDto(Set<Section> sections) {
        this.sections = sections.stream().map(SectionResponseDto::new).toList();
    }
}
