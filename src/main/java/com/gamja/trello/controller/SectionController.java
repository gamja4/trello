package com.gamja.trello.controller;

import com.gamja.trello.dto.response.SectionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamja.trello.dto.request.SectionRequestDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.SectionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class SectionController {
	private final SectionService sectionService;

	@PostMapping("/boards/{boardId}/sections")
	public ResponseEntity<SectionResponseDto> createSection(@PathVariable Long boardId,
															@RequestBody @Valid SectionRequestDto requestDto,
															@AuthenticationPrincipal UserDetailsImpl userDetails) {

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(sectionService.createSection(boardId, requestDto));
	}

	@DeleteMapping("/boards/{boardId}/sections/{sectionId}")
	public ResponseEntity<?> deleteSection(@PathVariable Long boardId,
		@PathVariable Long sectionId,
		@AuthenticationPrincipal UserDetailsImpl userDetails) {

		sectionService.deleteSection(boardId, sectionId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
