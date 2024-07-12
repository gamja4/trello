package com.gamja.trello.service;

import com.gamja.trello.dto.request.MoveSectionRequestDto;
import com.gamja.trello.dto.response.MoveSectionResponseDto;
import org.springframework.stereotype.Service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.SectionRequestDto;
import com.gamja.trello.dto.response.SectionResponseDto;
import com.gamja.trello.entity.Board;
import com.gamja.trello.entity.Section;
import com.gamja.trello.repository.SectionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	@Transactional
	public MoveSectionResponseDto moveSection(Long boardId, MoveSectionRequestDto requestDto) {
		List<Section> sections = boardService.findBoard(boardId).getSections();

		Map<Long, Integer> reqSectionMap = requestDto.getSections().stream()
				.collect(Collectors.toMap(MoveSectionRequestDto.section::getId, MoveSectionRequestDto.section::getSort));
		
		sections.stream().forEach(section -> {
			if(reqSectionMap.containsKey(section.getId())) {
				int sort = reqSectionMap.get(section.getId());
				section.move(sort);
			}
		});

		return new MoveSectionResponseDto(sections);

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
