package com.myself.myself.controller;
import com.myself.myself.dto.BoardFormDto;
import com.myself.myself.dto.ReplyFormDto;
import com.myself.myself.entity.Board;
import com.myself.myself.repository.boardRepository;
import com.myself.myself.repository.userRepository;
import com.myself.myself.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    boardRepository boardRepository;

    @Autowired
    userRepository userRepository;

    @GetMapping("/board")
    public String noticeboard(Model model, @PageableDefault(size = 4) Pageable pageable,@RequestParam(required = false, defaultValue = "") String searchText,
                              @RequestParam(required = false, defaultValue = "")String searchType){

        if(searchType.equals("content")){
            Page<Board> boards = boardRepository.findByContentContaining(searchText,pageable);
            int startPage = Math.max(1,boards.getPageable().getPageNumber() -4);
            int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() +4);
            model.addAttribute("startPage",startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("boards",boards);
            return "board";
        }else{
            Page<Board> boards = boardRepository.findByTitleContaining(searchText,pageable);
            int startPage = Math.max(1,boards.getPageable().getPageNumber() -4);
            int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber() +4);
            model.addAttribute("startPage",startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("boards",boards);
            return "board";
        }

    }

    @GetMapping("/board/{id}")
    public String findBoard(@PathVariable Long id,Model model){
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        model.addAttribute("replyFormDto", new ReplyFormDto());
        return "detail";
    }

    @GetMapping("/newBoard")
    public String newBoard(Model model){
        model.addAttribute("boardFormDto",new BoardFormDto());
        return "newboardwrite";
    }

    @PostMapping("/newBoard")
    public String boardPlus(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model, Authentication authentication){
        if(bindingResult.hasErrors()){
            return "newboardwrite";
        }
        try {
            String username = authentication.getName();
            Board board = Board.createBoard(boardFormDto);
            boardService.saveBoard(username,board);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "newboardwrite";
        }
        return "redirect:/board";
    }

    @GetMapping("/modify/{id}")
    public String editBoard(@PathVariable Long id,Model model){
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("editBoard",board);
        return "modify";
    }

    @PostMapping("/modify/{id}")
    public String finEdit(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "modify";
        }
        try {
            boardService.updateItem(boardFormDto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "modify";
        }
        return "redirect:/board";
    }

    @GetMapping("/delete/{id}")
    public String delBoard(BoardFormDto boardFormDto){
        boardService.deleteItem(boardFormDto);
        return "redirect:/board";
    }
}
