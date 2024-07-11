package com.gamja.trello.entity;

import com.gamja.trello.dto.request.CardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Card extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int sort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    public Card (CardRequestDto requestDto, Section section, User user){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.sort = 0;
        this.section = section;
        this.user = user;
    }

    @Builder
    public Card(Long id, String title, String content, int sort, User user, Section section) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sort = sort;
        this.user = user;
        this.section = section;
    }
}
