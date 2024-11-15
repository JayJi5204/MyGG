package com.project.mygg.controller;

import com.project.mygg.DTO.PlayerRequestDTO;
import com.project.mygg.DTO.PlayerResponseDTO;
import com.project.mygg.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
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
            return "/playerManage/addPlayer";
        }
        try {
            playerService.addPlayer(playerRequestDTO);
        } catch (IllegalArgumentException e) {
            String[] errorMessages = e.getMessage().split("\n");
            for (String message : errorMessages) {
                if (message.contains("닉네임")) {
                    result.rejectValue("nickname", "error.nickname", message);
                } else if (message.contains("티어")) {
                    result.rejectValue("tier", "error.tier", message);
                }
            }
            return "/playerManage/addPlayer";
        }

        return "redirect:/playerManage";
    }

    @GetMapping("/updatePlayer/{id}")
    public String getUpdateMember(@PathVariable Long id, Model model) {
        PlayerResponseDTO playerResponseDTO = playerService.findOne(id)
                .orElseThrow(() -> new EntityNotFoundException("선수를 찾을 수 없습니다."));
        model.addAttribute("player",playerResponseDTO);
        return "/playerManage/updatePlayer";
    }

    @PostMapping("/updatePlayer/{id}")
    public String postUpdateMember(@PathVariable Long id, @ModelAttribute("player") PlayerRequestDTO playerRequestDTO) {
        playerService.updatePlayer(id, playerRequestDTO);
        return "redirect:/updatePlayer";
    }


    @PostMapping("/deletePlayer/{id}")
    public String postDeletePlayer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            playerService.deletePlayer(id);
            redirectAttributes.addFlashAttribute("message", "선수가 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "선수 삭제 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "redirect:/playerManage";
    }

}
