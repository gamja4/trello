package com.gamja.trello.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
@Entity
public class BoardInvitation extends Timestamp{

    @EmbeddedId
    private BoardInvitationId id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id", insertable = false, updatable = false)
    private Board board;

    @Column(nullable = false)
    private BoardRole role;

    @Builder
    public BoardInvitation (User user, Board board, BoardRole role) {
        this.id = new BoardInvitationId(user, board);
        this.user = user;
        this.board = board;
        this.role = role;
    }

    // 보드내에서의 권한
    @Getter
    @RequiredArgsConstructor
    public enum BoardRole {
        NORMAL("NORMAL"), // 섹션 및 카드 및 댓글 작성을 할 수 있음, 본인 것 만 수정 가능
        EDITOR("EDITOR"), // 섹션 및 카드 및 댓글 수정 가능
        OWNER("OWNER"); // 보드의 모든 권한

        private final String roleName;
    }
}
