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
	private final BoardService boardService;

	public SectionResponseDto createSection(Long boardId, SectionRequestDto requestDto) {

		Board board = boardService.findBoard(boardId);
		Section section = Section.builder()
			 .board(board)
			.title(requestDto.getTitle())
			.sort(0)
			.build();

		sectionRepository.save(section);

		return new SectionResponseDto(section);
	}

	public void deleteSection(Long boardId, Long sectionId) {

		Board board = boardService.findBoard(boardId);
		Section section = sectionRepository.findSectionById(sectionId);
		checkBoardAndSection(board, section);

		sectionRepository.delete(section);
	}

	public Section findById(Long sectionId) {
		return sectionRepository.findSectionById(sectionId);
	}

	public void checkBoardAndSection(Board board, Section section) {
		if (!board.getId().equals(section.getBoard().getId())) {
			throw new CustomException(ErrorCode.SECTION_NOT_FOUND);
		}
	}
}
