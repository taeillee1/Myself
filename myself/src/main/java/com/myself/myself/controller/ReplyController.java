package com.myself.myself.controller;


import com.myself.myself.dto.BoardFormDto;
import com.myself.myself.dto.ReplyFormDto;
import com.myself.myself.entity.Board;
import com.myself.myself.entity.Reply;
import com.myself.myself.repository.boardRepository;
import com.myself.myself.service.BoardService;
import com.myself.myself.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    boardRepository boardRepository;

    @PostMapping("/newReply/{id}")
    public String newReply(@Valid ReplyFormDto replyFormDto, BindingResult bindingResult, Model model, Authentication authentication,@PathVariable Long id){
        if(bindingResult.hasErrors()){
            return "board";
        }
        try {
            String username = authentication.getName();
            Board board = boardRepository.findById(id).orElse(null);
            Long boardId = board.getId();
            Reply reply = Reply.createReply(replyFormDto);
            replyService.replyWrite(username,boardId,reply);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "board";
        }
        return "redirect:/board/{id}";
    }

    @GetMapping("/deleteReply/{id}")
    public String delReply(ReplyFormDto replyFormDto){
        replyService.deleteReply(replyFormDto);
        return "redirect:/board";

    }
}
