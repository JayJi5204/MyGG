package com.project.mygg.controller;

import com.project.mygg.DTO.replyDTO.ReplyRequestDTO;
import com.project.mygg.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;


    @PostMapping("/board/{id}/createReply")
    public String postCreateReply(@Valid @ModelAttribute("reply") ReplyRequestDTO replyRequestDTO, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "redirect:/board/" + id;
        }
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
