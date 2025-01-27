package com.gamja.trello.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class BoardInvitationId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Builder
    public BoardInvitationId(User user, Board board) {
        this.user = user;
        this.board = board;
    }

}
