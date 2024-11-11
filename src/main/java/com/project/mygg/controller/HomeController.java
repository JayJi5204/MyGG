package com.project.mygg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "/home/home";
    }
    @RequestMapping("/test")
    public String test(){
        return "/home/test";
    }

}
