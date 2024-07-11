package com.gamja.trello.Service;

import com.gamja.trello.dto.CardRequestDto;
import com.gamja.trello.dto.CardResponseDto;
import com.gamja.trello.entity.Board;
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
//    private final BoardRepository boardRepository;
//    private final SectionRepository sectionRepository;

    public CardResponseDto createCard(Long boardId, Long sectionId, CardRequestDto requestDto, User user) {
        Board board = findBoardById(boardId);
        Section section = findSectionById(sectionId);

        Card card = new Card(requestDto, board, section, user);
        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(card);

    }

    private Board findBoardById(Long boardId) {
        return null;
        // return boardRepository.findById(boardId).orElseThrow(() ->
        //      new IllegalArgumentException("해당 보드를 찾을 수 없습니다.))
    }

    private Section findSectionById(Long sectionId) {
        return null;
        // return sectionRepository.findById(sectionId).orElseThrow(() ->
        //      new IllegalArgumentException("해당 컬럼을 찾을 수 없습니다.))
    }

}
