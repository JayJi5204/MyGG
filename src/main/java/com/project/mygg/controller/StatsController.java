package com.project.mygg.controller;

import com.project.mygg.DTO.TotalKdaDTO;
import com.project.mygg.DTO.StatDTO;
import com.project.mygg.DTO.playerDTO.PlayerResponseDTO;
import com.project.mygg.DTO.statsDTO.StatsRequestDTO;
import com.project.mygg.DTO.statsDTO.StatsResponseDTO;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.service.PlayerService;
import com.project.mygg.service.StatsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;
    private final PlayerService playerService;

    @GetMapping("/createStats/{playerId}")
    public String getCreateStats(@PathVariable Long playerId,Model model) {
        List<PlayerResponseDTO> player = playerService.findPlayer();
        model.addAttribute("player", player);
        model.addAttribute("playerId", playerId);
        model.addAttribute("match", new StatsRequestDTO());

        return "/player/createStats";
    }


    @PostMapping("/createStats/{playerId}")
    public String postCreateStats(@Valid @ModelAttribute("match") StatsRequestDTO statsRequestDTO, BindingResult result
            , @PathVariable Long playerId, Model model) {

        model.addAttribute("playerId", playerId);
        if (result.hasErrors()) {
            List<PlayerResponseDTO> player = playerService.findPlayer();
            model.addAttribute("player", player);
            return "/player/createStats";
        }
        try {
            model.addAttribute("playerId", playerId);
            statsService.
                    createStats(playerId, statsRequestDTO);
        } catch (IllegalArgumentException e) {

            return "/player/createStats";
        }

        return "redirect:/searchPlayer";

    }


    @GetMapping("/searchPlayer")
    public String getSearchPlayer(Long playerId, Model model) {
        if (playerId != null) {
            List<StatDTO> searchPlayer = statsService.TotalKda(playerId);
            List<PlayerResponseDTO> player = playerService.findPlayer();
            List<TotalKdaDTO> kdaList = statsService.getTotalKda(playerId);
            model.addAttribute("kdaList", kdaList);
            model.addAttribute("player", player);
            model.addAttribute("search", searchPlayer);
        } else {
            List<PlayerResponseDTO> player = playerService.findPlayer();
            model.addAttribute("player", player);
        }
        return "/player/searchPlayer";
    }

}
