package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/memberManage")
    public String getMemberList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<MemberResponseDTO> paging = memberService.findMembers(page);
        model.addAttribute("paging", paging);
        return "/memberManage/memberManage";
    }

    @PostMapping("/deleteMember/{id}")
    public String deleteMember(Model model, @PathVariable Long id){
        memberService.deleteMember(id);
        return "redirect:/memberManage";
    }

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
        return "redirect:/memberManage";
    }




}
