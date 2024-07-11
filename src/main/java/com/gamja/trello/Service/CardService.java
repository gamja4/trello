package com.gamja.trello.Service;

import com.gamja.trello.dto.CardRequestDto;
import com.gamja.trello.dto.CardResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import com.gamja.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto/*, User user*/) {
        Section section = findSectionById(sectionId);
        Card card = new Card();

        return null;
    }

    private Section findSectionById(Long sectionId) {
        return null;
    }
}
