package com.project.mygg.controller;

import com.project.mygg.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PlayerSearchController {

    private final PlayerService playerService;

    @RequestMapping("/playerSearch")
    public String result(){
        return "/playerSearch/playerSearch";
    }

}
