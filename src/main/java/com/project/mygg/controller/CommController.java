package com.project.mygg.controller;

import com.project.mygg.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class CommController {

    private final SessionService sessionService;

    @RequestMapping("/")
    public String home() {

        System.out.println("===========================");
        System.out.println(sessionService.sessionRole());
        System.out.println("===========================");

        return "/comm/home";
    }

    @RequestMapping("/tierList")
    public String tierList(){
        return "/comm/tierList";
    }

    @RequestMapping("/ranking")
    public String ranking(){
        return "/comm/ranking";
    }

    @RequestMapping("/rule")
    public String rule(){
        return "/comm/rule";
    }

    @RequestMapping("/gameResult")
    public String result(){
        return "/comm/gameResult";
    }

    @RequestMapping("/myPage")
    public String myPage(){
        return "/comm/myPage";
    }
}
