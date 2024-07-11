package com.gamja.trello.controller;

import com.gamja.trello.Service.CardService;
import com.gamja.trello.dto.CardRequestDto;
import com.gamja.trello.dto.CardResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/columns/{columnId}")
    public CardResponseDto createCard(@PathVariable Long columnId,
                                      @Valid @RequestBody CardRequestDto requestDto/*,
                                      @AuthenticationPrincipal userDetailsImpl userDetails*/){
        return cardService.createCard(columnId, requestDto/*, userDetails.getUser()*/);
    }

}
