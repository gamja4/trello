package com.gamja.trello.service;

import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
//    private final SectionRespository sectionRespository;

    public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto, User user) {
        Section section = findSectionById(sectionId);
        Card card = new Card(requestDto, section, user);

        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(card);

    }

    private Section findSectionById(Long sectionId) {
         return null;
//        return sectionRepository.findById(sectionId).orElse(() ->
//                new IllegalArgumentException("해당 컬럼은 존재하지 않습니다."));
    }
}
