package com.project.mygg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResultManageController {
    @GetMapping("/resultManage")
    public String getResultManage(){
        return "/resultManage/resultManage";
    }

    @PostMapping("/resultManage")
    public String postResultManage(){
        return "/resultManage/resultManage";
    }
}
