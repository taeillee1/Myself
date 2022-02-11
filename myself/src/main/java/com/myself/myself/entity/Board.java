package com.myself.myself.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myself.myself.dto.BoardFormDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //연관관계의 주인이 아니다 DB에 칼럼을 만들지마라 board를 select할떄 값을 얻기위해 쓰는 겁니다
    @JsonIgnoreProperties({"board"})//무한참조를 막기위해 사용하는 어노테이션 board가 reply를 호출하고 reply안의 board가 또호출되고 계속 반복되는 것을 막기 위한 어노테이션
    @OrderBy("id desc")
    private List<Reply> replys;

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
