package com.myself.myself.controller;

import com.myself.myself.dto.UserFormDto;
import com.myself.myself.entity.User;
import com.myself.myself.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;


    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signpage(Model model){
        model.addAttribute("userFormDto",new UserFormDto());
        return "sign";
    }

    @PostMapping("/signup")
    public String sign(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "sign";
        }
        try {
            User user = User.createUser(userFormDto, passwordEncoder);
            userService.signup(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "sign";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }

}
