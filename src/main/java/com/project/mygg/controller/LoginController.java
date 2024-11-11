package com.project.mygg.controller;

import com.project.mygg.DTO.MemberRequestDTO;
import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @GetMapping("/signIn")
    public String getLogin(Model model) {

        return "/login/signIn";
    }


    @GetMapping("/signUp")
    public String getSingIn(Model model) {
        model.addAttribute("member", new MemberRequestDTO());
        return "/login/signUp";
    }

    //    @PostMapping("/signUp")
//    public String postSingIn(@Valid @ModelAttribute("member") MemberRequestDTO memberRequestDTO, BindingResult result) {
//        if (result.hasErrors()) {
//            return "/login/signUp";
//        }
//        memberService.signUp(memberRequestDTO);
//        return "redirect:/signUp";
//    }
    @PostMapping("/signUp")
    public String postSingIn(@Valid @ModelAttribute("member") MemberRequestDTO memberRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "/login/signUp";
        }
        memberService.signUp(memberRequestDTO);
        return "redirect:/signUp";
    }


    @GetMapping("/memberList")
    public String getMemberList(Model model, MemberResponseDTO memberResponseDTO) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/login/memberList";
    }
}

