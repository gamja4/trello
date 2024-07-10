package com.gamja.trello.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
@Entity
public class BoardInvitation extends Timestamp{

    @EmbeddedId
    private BoardInvitationId id;

    @Builder
    public BoardInvitation (User user, Board board) {
        this.id = new BoardInvitationId(user, board);
    }
}
