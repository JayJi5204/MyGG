package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @RequestMapping("/myPage")
    public String myPage(){
        return "/myPage/myPage";
    }
    @GetMapping("/updateMyPage")
    public String getUpdateMember(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);
        return "/myPage/updateMyPage";
    }
}
