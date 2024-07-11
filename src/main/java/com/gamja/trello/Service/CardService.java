package com.gamja.trello.service;

import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto, User user) {
        Section section = findSectionById(sectionId);

        if(requestDto.getTitle() == null || requestDto.getTitle().isEmpty() ||
            requestDto.getStatus() == null || requestDto.getStatus().isEmpty()){
            throw new IllegalArgumentException("제목과 카드 상태를 입력해주세요");
        }

        Card card = new Card(requestDto, section, user);
        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(card);
    }

    public CardResponseDto updateCard(Long sectionId, Long cardId, CardRequestDto requestDto, User user) {
        if(user == null){
            throw new IllegalArgumentException("로그인한 후에 수정해주세요");
        }
        Section section = findSectionById(sectionId);
        Card card = findCardById(cardId);

        card.update(requestDto);
        return new CardResponseDto(card);

    }

    private Card findCardById(Long cardId) {
//        return  cardRepository.findById(cardId).orElse(() ->
//                new IllegalArgumentException("해당 카드는 존재하지 않습니다."));
        return null;
    }

    private Section findSectionById(Long sectionId) {
         return null;
//        return sectionRepository.findById(sectionId).orElse(() ->
//                new IllegalArgumentException("해당 컬럼은 존재하지 않습니다."));
    }

}
