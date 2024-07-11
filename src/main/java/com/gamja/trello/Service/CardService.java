package com.gamja.trello.service;

import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.dto.response.MessageResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto, User user) {
        Section section = findSectionById(sectionId);

        Card card = new Card(requestDto, section, user);
        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(card);
    }

    @Transactional(readOnly = true)
    public List<CardResponseDto> getCards(Long sectionId, String writer, String status) {
        List<Card> cards;

        if(writer != null && status != null){
            cards = cardRepository.findByWriterAndStatusAndSectionId(writer, status, sectionId);
        } else if(writer != null){
            cards = cardRepository.findByWriterAndSectionId(writer,sectionId);
        }else if(status != null){
            cards = cardRepository.findByStatusAndSectionId(status, sectionId);
        }else{
            cards = cardRepository.findBySectionId(sectionId);
        }

        return cards.stream().map(CardResponseDto::new).toList();
    }

    public CardResponseDto updateCard(Long sectionId, Long cardId, CardRequestDto requestDto, User user) {
        Section section = findSectionById(sectionId);
        Card card = findCardById(cardId);

        card.update(requestDto);
        return new CardResponseDto(card);

    }

    public MessageResponseDto deleteCard(Long sectionId, Long cardId, User user) {
        Section section = findSectionById(sectionId);
        Card card = findCardById(cardId);

        cardRepository.delete(card);
        return new MessageResponseDto("카드가 삭제되었습니다");
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