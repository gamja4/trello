package com.gamja.trello.controller;

import com.gamja.trello.Service.CardService;
import com.gamja.trello.dto.CardRequestDto;
import com.gamja.trello.dto.CardResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
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
    public CardResponseDto createCard(@PathVariable Long boardId,
                                      @PathVariable Long sectionId,
                                      @Valid @RequestBody CardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return cardService.createCard(boardId, sectionId, requestDto, userDetails.getUser());
    }

}
