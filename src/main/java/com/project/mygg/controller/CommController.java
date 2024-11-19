package com.project.mygg.controller;

import com.project.mygg.DTO.PlayerDTO.PlayerResponseDTO;
import com.project.mygg.enums.Tier;
import com.project.mygg.service.PlayerService;
import com.project.mygg.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommController {

    private final SessionService sessionService;
    private final PlayerService playerService;

    @RequestMapping("/")
    public String home() {
        return "/comm/home";
    }

    @RequestMapping("/tierList")
    public String tierList(Model model) {
        List<PlayerResponseDTO> player = playerService.tierList();
        model.addAttribute("player", player);
        return "/comm/tierList";
    }

    @RequestMapping("/ranking")
    public String ranking() {
        return "/comm/ranking";
    }

    @RequestMapping("/rule")
    public String rule() {
        return "/comm/rule";
    }

    @RequestMapping("/gameResult")
    public String result() {
        return "/comm/gameResult";
    }


}
