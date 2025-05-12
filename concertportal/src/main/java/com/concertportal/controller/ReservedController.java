package com.concertportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/reserved")
public class ReservedController {

    @GetMapping
    public String renderPage() {
        return "reserved";
    }
    
}
