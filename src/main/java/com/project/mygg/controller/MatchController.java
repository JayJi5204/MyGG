package com.project.mygg.controller;

import com.project.mygg.DTO.StatsDTO.StatsRequestDTO;
import com.project.mygg.DTO.matchDTO.MatchRequestDTO;
import com.project.mygg.DTO.playerDTO.PlayerRequestDTO;
import com.project.mygg.service.MatchService;
import com.project.mygg.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final PlayerService playerService;

    @GetMapping("/createMatch")
    public String getCreateMatch(Model model) {

        model.addAttribute("match", new MatchRequestDTO());

        return "/match/createMatch";
    }


    @PostMapping("/createMatch")
    public String postCreateMatch(@Valid @ModelAttribute MatchRequestDTO matchRequestDTO, PlayerRequestDTO playerRequestDTO
            , StatsRequestDTO statsRequestDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "/match/createMatch";
        }
        try {
            matchService.addMatch(playerRequestDTO, statsRequestDTO, matchRequestDTO);
        } catch (IllegalArgumentException e) {

            return "/match/createMatch";
        }

        return "redirect:/";

    }

}
