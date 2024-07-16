package com.gamja.trello.service;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.request.MoveCardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.dto.response.MoveCardResponseDto;
import com.gamja.trello.entity.Card;
import com.gamja.trello.entity.Section;
import com.gamja.trello.entity.User;
import com.gamja.trello.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final SectionService sectionService;


    public CardResponseDto createCard(Long sectionId, CardRequestDto requestDto, User user) {
        Section section = sectionService.findById(sectionId);

        Card card = new Card(requestDto, section, user);
        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(saveCard);
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

    @Transactional
    public CardResponseDto updateCard(Long sectionId, Long cardId, CardRequestDto requestDto, User user) {
        Section section = sectionService.findById(sectionId);
        Card card = findCardById(cardId);

        card.update(requestDto);
        return new CardResponseDto(card);

    }

    public void deleteCard(Long sectionId, Long cardId, User user) {
        Section section = sectionService.findById(sectionId);
        Card card = findCardById(cardId);

        cardRepository.delete(card);
    }

    public Card findCardById(Long cardId) {
        return cardRepository.findCardById(cardId);
    }

    @Transactional
    public MoveCardResponseDto moveCard(Long sectionId, MoveCardRequestDto req, User user) {
        // section에 대한 card들을 가져온다
//        List<Card> cards = sectionService.findById(sectionId).getCards();

        // section에 대한 card들이 아닌, req의 id card들을 가져온다.
        List<Long> cardIds = req.getCards().stream().map(MoveCardRequestDto.card::getId).toList();

        List<Card> cards = cardRepository.findByIdIn(cardIds);

        Map<Long, Integer> reqCardMap = req.getCards().stream()
                .collect(Collectors.toMap(MoveCardRequestDto.card::getId, MoveCardRequestDto.card::getSort));

        // 해당 card와 dto card를 매핑시켜야함
        cards.forEach(card -> {
            if (reqCardMap.containsKey(card.getId())) {
                int sort = reqCardMap.get(card.getId());
                card.move(sort, sectionId);
            } else {
                throw new CustomException(ErrorCode.FAIL);
            }
        });

        return new MoveCardResponseDto(cards);
    }
}
