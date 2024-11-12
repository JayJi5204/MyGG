package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberListController {

    private final MemberService memberService;

    @GetMapping("/memberList")
    public String getMemberList(Model model, MemberResponseDTO memberResponseDTO) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/login/memberList";
    }


}
