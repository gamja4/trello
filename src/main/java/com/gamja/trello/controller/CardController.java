package com.gamja.trello.controller;

import com.gamja.trello.dto.request.CardRequestDto;
import com.gamja.trello.dto.request.MoveCardRequestDto;
import com.gamja.trello.dto.response.CardResponseDto;
import com.gamja.trello.dto.response.MoveCardResponseDto;
import com.gamja.trello.security.service.UserDetailsImpl;
import com.gamja.trello.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{boardId}/sections/{sectionId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<?> createCard(@PathVariable Long sectionId,
                                      @Valid @RequestBody CardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.status(HttpStatus.CREATED).body(cardService.createCard(sectionId, requestDto, userDetails.getUser()));
    }

    @GetMapping
    public List<CardResponseDto> getCards(@PathVariable Long sectionId,
                                          @RequestParam(required = false) String writer,
                                          @RequestParam(required = false) String status){
        return cardService.getCards(sectionId, writer, status);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<?> updateCard(@PathVariable Long sectionId,
                                      @PathVariable Long cardId,
                                      @Valid @RequestBody CardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails){

        return ResponseEntity.status(HttpStatus.OK).body(cardService.updateCard(sectionId, cardId, requestDto, userDetails.getUser()));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long sectionId,
                                        @PathVariable Long cardId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        cardService.deleteCard(sectionId, cardId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<MoveCardResponseDto> moveCard(@PathVariable("sectionId") Long sectionId,
                                                        @RequestBody MoveCardRequestDto req,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(cardService.moveCard(sectionId, req, userDetails.getUser()));
    }
}
