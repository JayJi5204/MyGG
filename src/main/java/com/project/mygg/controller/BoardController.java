package com.project.mygg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    @GetMapping("/board")
    public String getResultManage(){
        return "/board/board";
    }

    @PostMapping("/board")
    public String postResultManage(){
        return "/board/board";
    }
}
