package com.project.mygg.controller;

import com.project.mygg.DTO.ResultDTO.ResultRequestDTO;
import com.project.mygg.DTO.ResultDTO.ResultResponseDTO;
import com.project.mygg.DTO.PlayerDTO.PlayerResponseDTO;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.service.ResultService;
import com.project.mygg.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;
    private final PlayerService playerService;

    @RequestMapping("/gameResult")
    public String gameResult(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<ResultResponseDTO> paging = resultService.findResult(page);
        model.addAttribute("paging", paging);
        return "/resultManage/gameResult";
    }

    @GetMapping("/createResult")
    public String getCreateResult(Model model) {
        List<PlayerResponseDTO> player = playerService.findPlayer();
        model.addAttribute("player", player);
        model.addAttribute("result", new ResultRequestDTO());
        return "/resultManage/createResult";
    }

    @PostMapping("/createResult")
    public String postCreateResult(@Valid @ModelAttribute("result") ResultRequestDTO resultRequestDTO,BindingResult result, PlayerEntity playerEntity,Model model) {
        if (result.hasErrors()) {
            List<PlayerResponseDTO> player = playerService.findPlayer();
            model.addAttribute("player", player);
            return "/resultManage/createResult";
        }
            resultService.createResult(resultRequestDTO, playerEntity);
        return "redirect:/gameResult";
    }
}
