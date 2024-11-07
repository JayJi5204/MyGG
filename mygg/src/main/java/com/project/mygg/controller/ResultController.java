package com.project.mygg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResultController {
    @GetMapping("/resultManage")
    public String getResultManage(){
        return "/resultManage";
    }

    @PostMapping("/resultManage")
    public String postResultManage(){
        return "/resultManage";
    }
}
