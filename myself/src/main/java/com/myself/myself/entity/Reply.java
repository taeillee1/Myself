package com.myself.myself.entity;

import com.myself.myself.dto.BoardFormDto;
import com.myself.myself.dto.ReplyFormDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne// 여러개의 답변은 하나의 게시글에 존재가능
    @JoinColumn(name = "boardId")
    private Board board; //BOard에서 mappedby뒤에 적는 board는 이것을 의미한다

    @ManyToOne// 여러개의 답변은 하나의 유저가 쓸수있다.
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public static Reply createReply(ReplyFormDto replyFormDto){
        Reply reply = new Reply();
        reply.setContent(replyFormDto.getContent());
        return reply;
    }

}