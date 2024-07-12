package com.gamja.trello.repository;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    default Card findCardById(Long id){
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.CARD_NOT_FOUND)
        );
    }

    List<Card> findByWriterAndStatusAndSectionId(String writer, String status, Long sectionId);

    List<Card> findByWriterAndSectionId(String writer, Long sectionId);

    List<Card> findByStatusAndSectionId(String status, Long sectionId);

    List<Card> findBySectionId(Long sectionId);

}
