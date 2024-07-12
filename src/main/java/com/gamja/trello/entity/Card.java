package com.gamja.trello.entity;

import com.gamja.trello.dto.request.CardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt = LocalDateTime.now();


    public Card (CardRequestDto requestDto, Section section, User user){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.status = requestDto.getStatus();
        this.dueDate = requestDto.getDueDate();
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

    public void update(CardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.status = requestDto.getStatus();
        this.dueDate = requestDto.getDueDate();
        this.modifiedAt= LocalDateTime.now();
    }

    public void move(int sort) {
        this.sort = sort;
    }
}
