package com.gamja.trello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardInvitationId is a Querydsl query type for BoardInvitationId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBoardInvitationId extends BeanPath<BoardInvitationId> {

    private static final long serialVersionUID = -1506562780L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardInvitationId boardInvitationId = new QBoardInvitationId("boardInvitationId");

    public final QBoard board;

    public final QUser user;

    public QBoardInvitationId(String variable) {
        this(BoardInvitationId.class, forVariable(variable), INITS);
    }

    public QBoardInvitationId(Path<? extends BoardInvitationId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardInvitationId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardInvitationId(PathMetadata metadata, PathInits inits) {
        this(BoardInvitationId.class, metadata, inits);
    }

    public QBoardInvitationId(Class<? extends BoardInvitationId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

