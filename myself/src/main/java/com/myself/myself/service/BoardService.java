package com.myself.myself.service;

import com.myself.myself.dto.BoardFormDto;
import com.myself.myself.entity.Board;
import com.myself.myself.entity.User;
import com.myself.myself.repository.boardRepository;
import com.myself.myself.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private boardRepository boardRepository;

    @Autowired
    private userRepository userRepository;


    @Transactional
    public Board saveBoard(String username,Board board){
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateItem(BoardFormDto boardFormDto){
        Board board = boardRepository.findById(boardFormDto.getId()).orElseThrow(null);
        board.updateBoard(boardFormDto);
        return board;
    }

    @Transactional
    public void deleteItem(BoardFormDto boardFormDto){
        Board board = boardRepository.findById(boardFormDto.getId()).orElseThrow(null);
        boardRepository.delete(board);
    }





}
