package com.example.team1capstone.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){  //기본 주소 요청이 오면 index() 호출이 되고 index.html을 반환
        return "index";
    }
}
