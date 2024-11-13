package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import com.project.mygg.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final SessionService sessionService;

    // 회원 닉네임 수정
    @GetMapping("/updateMember")
    public String getUpdateMember(Model model, Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);
        return "/myPage/updateMember";
    }

    @PostMapping("/updateMember")
    public String postUpdateMember(){
        return "/myPage/updateMember";
    }


}
