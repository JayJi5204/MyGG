package com.project.mygg.controller;

import com.project.mygg.DTO.memberDTO.MemberRequestDTO;
import com.project.mygg.DTO.memberDTO.MemberResponseDTO;
import com.project.mygg.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String deleteMember(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            memberService.deleteMember(id);
            redirectAttributes.addFlashAttribute("message", "회원이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/memberManage";
    }

    // 회원 수정
    @GetMapping("/updateMember/{id}")
    public String getUpdateMember(@PathVariable Long id, Model model) {
        MemberResponseDTO memberResponseDTO = memberService.findOne(id)
                .orElseThrow(() -> new EntityNotFoundException("회원 찾을 수 없습니다."));
        model.addAttribute("member", memberResponseDTO);
        return "/memberManage/updateMember";
    }

    @PostMapping("/updateMember/{id}")
    public String postUpdateMember(@PathVariable Long id, @ModelAttribute("member") MemberRequestDTO memberRequestDTO, RedirectAttributes redirectAttributes) {
        memberService.updateMember(id, memberRequestDTO);
        redirectAttributes.addFlashAttribute("message", "회원이 성공적으로 변경되었습니다.");
        return "redirect:/memberManage";
    }

}
