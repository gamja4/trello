package com.gamja.trello.repository;

import com.gamja.trello.entity.BoardInvitation;
import com.gamja.trello.entity.BoardInvitationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardInvitationRepository extends JpaRepository<BoardInvitation, BoardInvitationId>{
    List<BoardInvitation> findById_UserId(Long userId);
    List<BoardInvitation> findOneById_BoardId(Long boardId);
}
