package com.myself.myself.controller;

import com.myself.myself.dto.UserFormDto;
import com.myself.myself.entity.User;
import com.myself.myself.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String sign(UserFormDto userFormDto){
        User user = User.createUser(userFormDto, passwordEncoder);
        userService.signup(user);

        return "redirect:/";
    }
}
