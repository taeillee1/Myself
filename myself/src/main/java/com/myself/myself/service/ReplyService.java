package com.myself.myself.service;

import com.myself.myself.dto.BoardFormDto;
import com.myself.myself.dto.ReplyFormDto;
import com.myself.myself.entity.Board;
import com.myself.myself.entity.Reply;
import com.myself.myself.entity.User;
import com.myself.myself.repository.boardRepository;
import com.myself.myself.repository.replyRepository;
import com.myself.myself.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    @Autowired
    private boardRepository boardRepository;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private replyRepository replyRepository;

    @Transactional
    public void replyWrite(String username, Long boardId, Reply requestReply){

        User user = userRepository.findByUsername(username);

        Board board = boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패");
        });
        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);

    }

    @Transactional
    public void deleteReply(ReplyFormDto replyFormDto){
        Reply reply = replyRepository.findById(Math.toIntExact(replyFormDto.getId())).orElseThrow(null);
        replyRepository.delete(reply);
    }
}
