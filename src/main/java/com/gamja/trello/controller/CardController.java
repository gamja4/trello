package com.gamja.trello.controller;

import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/sections/{sectionId}")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/cards")
    public CardResponseDto createCard(@PathVariable Long sectionId,
                                      @Valid @RequestBody CardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return cardService.createCard(sectionId, requestDto, userDetails.getUser());
    }

    @PutMapping("/cards/{cardId}")
    public CardResponseDto updateCard(@PathVariable Long sectionId,
                                      @PathVariable Long cardId,
                                      @Valid @RequestBody CardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return cardService.updateCard(sectionId, cardId, requestDto, userDetails.getUser());
    }

}
