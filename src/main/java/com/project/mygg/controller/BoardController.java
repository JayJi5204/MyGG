package com.project.mygg.controller;

import com.project.mygg.DTO.BoardDTO.BoardRequestDTO;
import com.project.mygg.DTO.BoardDTO.BoardResponseDTO;
import com.project.mygg.service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String getBoardList(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<BoardResponseDTO> paging = boardService.findBoards(page);
        model.addAttribute("paging", paging);
        return "/board/board";
    }
    @GetMapping("/createBoard")
    public String getCreatePage(Model model) {
        model.addAttribute("board", new BoardRequestDTO());
        return "/board/createBoard";
    }

    @PostMapping("/createBoard")
    public String postCreatePage(@ModelAttribute BoardRequestDTO boardRequestDTO) {
        boardService.createBoard(boardRequestDTO);
        return "redirect:/board"; // 메인 페이지로 리다이렉트
    }

    @GetMapping("/board/{id}")
    public String getBoardPage(@PathVariable Long id, Model model) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        model.addAttribute("board", boardResponseDTO);
        return "/board/boardPage";
    }
    @GetMapping("/updateBoard/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model,@AuthenticationPrincipal UserDetails userDetails) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));;
        if(!userDetails.getUsername().equals(boardResponseDTO.getWriter())) {
            return "redirect:/board/" + id;}
        model.addAttribute("board", boardResponseDTO);
        return "/board/updateBoard"; // 수정 페이지로 이동
    }

    @PostMapping("/updateBoard/{id}")
    public String postUpdatePage(@PathVariable Long id,@ModelAttribute("board") BoardRequestDTO boardRequestDTO) {
        boardService.updateBoard(id, boardRequestDTO);
        return "redirect:/board/" + id; // 수정된 게시글 페이지로 리다이렉트
    }

    // 게시글 삭제 페이지
    @PostMapping("/deleteBoard/{id}")
    public String postDeletePage(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board"; // 삭제 후 메인 페이지로 리다이렉트
    }

}
