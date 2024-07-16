package com.gamja.trello.repository.impl;

import com.gamja.trello.dto.BoardDto;
import com.gamja.trello.dto.response.BoardReadResponseDto;
import com.gamja.trello.entity.*;
import com.gamja.trello.repository.BoardCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * board 조회 시 fetch join을 활용하여 페이지네이션
     * offset을 사용한 페이지네이션 - 실패 => fetch join에는 limit 적용이 안된다.
     */
    public Page<Board> getFetchJoinBoard(Long id, int page, int size) {
        QBoard board = QBoard.board;
        QSection section = QSection.section;
        QCard card = QCard.card;

        // count
        JPAQuery<Long> queryCount = jpaQueryFactory
                .select(board.count())
                .from(board)
                .where(
                        board.id.eq(id)
                );

        // fetch join
        List<Board> boards = jpaQueryFactory
                .selectFrom(board)
                .leftJoin(board.sections, section).fetchJoin()
                .leftJoin(section.cards, card).fetchJoin()
                .where(board.id.eq(id))
                .offset((page - 1) * size) // 페이지네이션을 위한 offset
                .limit(size) // 페이지네이션을 위한 limit
                .distinct() // 중복 제거
                .fetch();

        Pageable pageable = PageRequest.of(page-1, size);

        return new PageImpl<>(boards, pageable, queryCount.fetchOne());
    }

    /**
     * board 조회 시 section, card 분리해서 조회
     */
    public BoardDto getFetchJoinBoard(Long id, Pageable pageable) {
        QBoard qboard = QBoard.board;
        QSection qsection = QSection.section;
        QCard qcard = QCard.card;

        // qboard 조회
        Board board = jpaQueryFactory
                .selectFrom(qboard)
                .where(qboard.id.eq(id))
                .fetchOne();

        Page<BoardDto.SectionDto> sections = selectSections(board.getId(), qsection, pageable);
        // 카드 조회시에는 나온 section의 id 값을 조회한다.
        List<Long> sectionIds = sections.getContent().stream().map(BoardDto.SectionDto::getId).toList();
        Page<BoardDto.CardDto> cards = selectCards(sectionIds, qcard, pageable);

        return new BoardDto(board, sections, cards);
    }

    // section count 조회
    private JPAQuery<Long> getSectionQueryCount(Long boardId, QSection section) {
        return jpaQueryFactory
                .select(section.count())
                .from(section)
                .where(
                        section.board.id.eq(boardId)
                );
    }

    // card count 조회
    private JPAQuery<Long> getCardQueryCount(List<Long> sectionIds, QCard card) {
        return jpaQueryFactory
                .select(card.count())
                .from(card)
                .where(
                        card.section.id.in(sectionIds)
                );
    }

    // section select 조회
    private Page<BoardDto.SectionDto> selectSections(Long boardId, QSection section, Pageable pageable) {

        List<BoardDto.SectionDto> sections = jpaQueryFactory.select(
                        Projections.constructor(BoardDto.SectionDto.class,
                                section.id,
                                section.title
                                )
                )
                .from(section)
                .where(section.board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(sections, pageable, () -> getSectionQueryCount(boardId, section).fetchFirst());
    }

    // section select 조회
    private Page<BoardDto.CardDto> selectCards(List<Long> sectionIds, QCard card, Pageable pageable) {

        List<BoardDto.CardDto> cards = jpaQueryFactory.select(
                    Projections.constructor(BoardDto.CardDto.class,
                            card.id,
                            card.section.id,
                            card.title,
                            card.content,
                            card.sort,
                            card.dueDate,
                            card.status,
                            card.writer,
                            card.createdAt,
                            card.modifiedAt
                    )
                )
                .from(card)
                .where(card.section.id.in(sectionIds))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return PageableExecutionUtils.getPage(cards, pageable, () -> getCardQueryCount(sectionIds, card).fetchFirst());
    }
}
