package com.project.mygg.controller;

import com.project.mygg.DTO.MemberDTO.MemberRequestDTO;
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

@Controller
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final MemberService memberService;
    private final SessionService sessionService;

    // 로그인 기능
    @GetMapping("/signIn")
    public String getSignIn(Model model) {
        model.addAttribute("id", sessionService.sessionName());
        return "/login/signIn";
    }

    // 회원가입 기능
    @GetMapping("/signUp")
    public String getSignUp(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        return "/login/signUp";
    }

    @PostMapping("/signUp")
    public String postSignUp(@Valid @ModelAttribute("member") MemberRequestDTO memberRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "/login/signUp";
        }

        try {
            memberService.signUp(memberRequestDTO);
        } catch (IllegalArgumentException e) {
            String[] errorMessages = e.getMessage().split("\n");
            for (String message : errorMessages) {
                if (message.contains("아이디")) {
                    result.rejectValue("username", "error.username", message);
                } else if (message.contains("휴대폰 번호")) {
                    result.rejectValue("phoneNumber", "error.phoneNumber", message);
                } else if (message.contains("비밀번호")) {
                    result.rejectValue("checkPassword", "error.checkPassword", message);
                }
            }
            return "/login/signUp";
        }

        return "redirect:/signIn";
    }


    // 로그아웃 기능
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

}

