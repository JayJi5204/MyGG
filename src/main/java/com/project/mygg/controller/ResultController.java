package com.project.mygg.controller;

import com.project.mygg.DTO.playerDTO.PlayerResponseDTO;
import com.project.mygg.DTO.resultDTO.ResultRequestDTO;
import com.project.mygg.DTO.resultDTO.ResultResponseDTO;
import com.project.mygg.service.PlayerService;
import com.project.mygg.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ResultController {

    private final ResultService resultService;
    private final PlayerService playerService;

    @GetMapping("/result")
    public String getResult(String option, String keyword, Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        if (keyword != null) {
            Page<ResultResponseDTO> searchBoard = resultService.searchResult(option, keyword, page);
            model.addAttribute("paging", searchBoard);
        } else {
            Page<ResultResponseDTO> paging = resultService.findResult(page);
            model.addAttribute("paging", paging);
        }

        return "/result/result";
    }

    @GetMapping("/createResult")
    public String getCreateResult(Model model) {
        List<PlayerResponseDTO> player = playerService.findPlayer();
        model.addAttribute("player", player);
        model.addAttribute("result", new ResultRequestDTO());
        return "/result/createResult";
    }

    @PostMapping("/createResult")
    public String postCreateResult(@Valid @ModelAttribute("result") ResultRequestDTO resultRequestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "/result/createResult";
        }
        resultService.createResult(resultRequestDTO);
        return "redirect:/result";
    }


    @GetMapping("/result/{id}")
    public String getBoardResult(@PathVariable Long id, Model model) {
        ResultResponseDTO resultResponseDTO = resultService.getResult(id).orElseThrow();
        model.addAttribute("result", resultResponseDTO);
        return "/result/resultPage";
    }

    @GetMapping("/updateResult/{id}")
    public String getUpdateResult(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        ResultResponseDTO resultResponseDTO = resultService.getResult(id).orElseThrow();

        if (!userDetails.getUsername().equals(resultResponseDTO.getWriter())) {
            return "redirect:/result/" + id;
        }
        List<PlayerResponseDTO> player = playerService.findPlayer();
        model.addAttribute("player", player);
        model.addAttribute("result", resultResponseDTO);
        return "/result/updateResult";
    }

    @PostMapping("/updateResult/{id}")
    public String postUpdateResult(@PathVariable Long id, @ModelAttribute("result") ResultRequestDTO resultRequestDTO) {
        resultService.updateResult(id, resultRequestDTO);
        return "redirect:/result/" + id;
    }

    // 게시글 삭제 페이지
    @PostMapping("/deleteResult/{id}")
    public String postDeleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return "redirect:/result";
    }
}
