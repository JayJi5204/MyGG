package com.project.mygg.controller;

import com.project.mygg.DTO.MemberResponseDTO;
import com.project.mygg.DTO.PlayerRequestDTO;
import com.project.mygg.DTO.PlayerResponseDTO;
import com.project.mygg.service.PlayerService;
import com.project.mygg.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
public class PlayerController {

    private final PlayerService playerService;
    private final SessionService sessionService;

    @GetMapping("/playerManage")
    public String getMemberList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<PlayerResponseDTO> paging = playerService.findPlayers(page);
        model.addAttribute("paging", paging);
        return "/playerManage/playerManage";
    }

    // 선수등록 기능
    @GetMapping("/addPlayer")
    public String getAddPlayer(Model model) {
        model.addAttribute("player", new PlayerRequestDTO());
        return "/playerManage/addPlayer";
    }

    @PostMapping("/addPlayer")
    public String postAddPlayer(@Valid @ModelAttribute("player") PlayerRequestDTO playerRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            log.info("already Valid");
            return "/playerManage/addPlayer";
        }

        try {
            playerService.addPlayer(playerRequestDTO);
        } catch (IllegalArgumentException e) {
            String[] errorMessages = e.getMessage().split("\n");
            for (String message : errorMessages) {
                if (message.contains("닉네임")) {
                    result.rejectValue("nickName", "error.nickName", message);
                }
            }
            return "/playerManage/addPlayer";
        }

        return "redirect:/playerManage";
    }

    @PostMapping("/deletePlayer/{id}")
    public String postDeletePlayer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Attempting to delete player with ID: " + id); // 로그 추가
            playerService.deletePlayer(id);
            redirectAttributes.addFlashAttribute("message", "선수가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            System.err.println("Error occurred while deleting player: " + e.getMessage()); // 로그 추가
            redirectAttributes.addFlashAttribute("errorMessage", "선수 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/playerManage";
    }

}
