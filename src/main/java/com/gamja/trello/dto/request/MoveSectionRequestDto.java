package com.gamja.trello.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class MoveSectionRequestDto {

    List<section> sections;

    @Getter
    public static class section {
        private Long id;
        private int sort;
    }}
