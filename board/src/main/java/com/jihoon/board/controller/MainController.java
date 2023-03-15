package com.jihoon.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apis")
public class MainController {
    
    @GetMapping("")
    public String hello() {
        return "Hello";
    }

}
