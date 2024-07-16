package com.gamja.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

	default Section findSectionById(Long id) {
		return findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.SECTION_NOT_FOUND)
		);
	}
}
