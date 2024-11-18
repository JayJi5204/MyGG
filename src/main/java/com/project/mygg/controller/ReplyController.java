package com.project.mygg.controller;

import com.project.mygg.DTO.BoardDTO.BoardResponseDTO;
import com.project.mygg.DTO.ReplyDTO.ReplyRequestDTO;
import com.project.mygg.DTO.ReplyDTO.ReplyResponseDTO;
import com.project.mygg.service.BoardService;
import com.project.mygg.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final BoardService boardService;


    @PostMapping("/board/{id}/createReply")
    public String postCreateReply(@PathVariable Long id, @ModelAttribute ReplyRequestDTO replyRequestDTO) {
        replyService.createReply(id, replyRequestDTO);
        return "redirect:/board/" + id;
    }


    /**
     * 나중에 비동기 통신이 되면
     * 그때 댓글 수정을 구현하자
     * 타임리프로는 도저히 구현이 안된다......
     */
    // 댓글 수정 페이지
//    @PostMapping("/updateReply/{id}")
//    public String postReply(@PathVariable Long id, @RequestParam Long brdId, @ModelAttribute("reply") ReplyRequestDTO replyRequestDTO) {
//        replyService.updateReply(id, replyRequestDTO);
//        return "redirect:/board/" + brdId;
//    }

    /**
     * 수정을 막아놔서 삭제도 막아놨다.....
     * 작동은 된다......
     */
    // 댓글 삭제 페이지
//    @PostMapping("/deleteReply/{id}")
//    public String postDeleteReply(@PathVariable Long id, @RequestParam Long brdId) {
//        replyService.deleteReply(id);
//        return "redirect:/board/" + brdId;
//    }

}
