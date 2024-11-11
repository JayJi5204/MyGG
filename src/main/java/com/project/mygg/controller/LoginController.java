package com.project.mygg.controller;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "/login/login";
    }

    @PostMapping("/login")
    public String postLogin(Model model) {
        return "redirect:/";
    }

    @GetMapping("/signUp")
    public String getSingIn(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        return "/login/signUp";
    }

    @PostMapping("/signUp")
    public String postSingIn(@Valid MemberRequestDTO memberRequestDTO, BindingResult result) {
        if(result.hasErrors()){
            return "/login/signUp";
        }
        memberService.signUp(memberRequestDTO);
        return "redirect:/login";
    }
}

