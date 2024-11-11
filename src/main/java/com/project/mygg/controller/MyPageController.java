package com.project.mygg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/myPage")
    public String myPage(){
        return "/myPage";
    }
}
