package com.myself.myself.entity;

import com.myself.myself.dto.BoardFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Board createBoard(BoardFormDto boardFormDto){
        Board board = new Board();
        board.setTitle(boardFormDto.getTitle());
        board.setContent(boardFormDto.getContent());
        return board;
    }

    public void updateBoard(BoardFormDto boardFormDto){
        this.title= boardFormDto.getTitle();
        this.content = boardFormDto.getContent();
    }
}
