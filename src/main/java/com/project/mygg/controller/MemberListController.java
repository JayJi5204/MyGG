package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberListController {

    private final MemberService memberService;

    @GetMapping("/memberList")
    public String getMemberList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<MemberResponseDTO> paging = memberService.findMembers(page);
        model.addAttribute("paging", paging);
        return "/memberList/memberList";
    }

    @PostMapping("/deleteMember/{id}")
    public String deleteMember(Model model, @PathVariable Long id){
        memberService.deleteMember(id);
        return "redirect:/memberList";
    }


}
