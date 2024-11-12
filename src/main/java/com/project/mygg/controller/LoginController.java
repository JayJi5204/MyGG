package com.project.mygg.controller;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import com.project.mygg.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final MemberService memberService;
    private final SessionService sessionService;

    @GetMapping("/signIn")
    public String getLogin(Model model) {
        model.addAttribute("id", sessionService.sessionName());
        System.out.println("===========================");
        System.out.println(sessionService.sessionRole());
        System.out.println("===========================");

        return "/login/signIn";
    }


    @GetMapping("/signUp")
    public String getSingIn(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        return "/login/signUp";
    }


    @PostMapping("/signUp")
    public String postSingIn(@Valid @ModelAttribute("member") MemberRequestDTO memberRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
           return "/login/signUp";
        }
        memberService.singUp(memberRequestDTO);

        return "redirect:/signIn";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    //    @PostMapping("/signUp")
//    public String postSingIn(@Valid @ModelAttribute("member") MemberRequestDTO memberRequestDTO, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/login/signUp";
//        }
//        memberService.signUp2(memberRequestDTO);
//        return "redirect:/signIp";
//    }



}

