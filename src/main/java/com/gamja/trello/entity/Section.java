package com.gamja.trello.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Section extends Timestamp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, name = "title")
	private String title;

	@Column(nullable = false)
	private int sort;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "boardId", nullable = false)
	private Board board;

	@OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Card> cards = new ArrayList<>();

	public void addCardList(Card card) {
		this.cards.add(card);
	}

	@Builder
	public Section(Long id, String title, int sort, Board board) {
		this.id = id;
		this.title = title;
		this.sort = sort;
		this.board = board;
	}

	public void move(int sort){
		this.sort = sort;
	}
}
