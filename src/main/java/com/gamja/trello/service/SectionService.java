package com.gamja.trello.service;

import org.springframework.stereotype.Service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.SectionRequestDto;
import com.gamja.trello.dto.response.SectionResponseDto;
import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Section;
import com.gamja.trello.repository.SectionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SectionService {

	private final SectionRepository sectionRepository;
	//private final BoardService boardService;

	public SectionResponseDto createSection(Long boardId, SectionRequestDto requestDto) {

		//Board board = boardService.findById(boardId);
		Section section = Section.builder()
			// .board(board)
			.title(requestDto.getTitle())
			.sort(0)
			.build();

		sectionRepository.save(section);

		return new SectionResponseDto(section);
	}

	public Section findById(Long sectionId) {
		return sectionRepository.findSectionById(sectionId);
	}

}
