package com.gamja.trello.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardInvitation is a Querydsl query type for BoardInvitation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardInvitation extends EntityPathBase<BoardInvitation> {

    private static final long serialVersionUID = -882013655L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardInvitation boardInvitation = new QBoardInvitation("boardInvitation");

    public final QTimestamp _super = new QTimestamp(this);

    public final QBoard board;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QBoardInvitationId id;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<BoardInvitation.BoardRole> role = createEnum("role", BoardInvitation.BoardRole.class);

    public final QUser user;

    public QBoardInvitation(String variable) {
        this(BoardInvitation.class, forVariable(variable), INITS);
    }

    public QBoardInvitation(Path<? extends BoardInvitation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardInvitation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardInvitation(PathMetadata metadata, PathInits inits) {
        this(BoardInvitation.class, metadata, inits);
    }

    public QBoardInvitation(Class<? extends BoardInvitation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board")) : null;
        this.id = inits.isInitialized("id") ? new QBoardInvitationId(forProperty("id"), inits.get("id")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

