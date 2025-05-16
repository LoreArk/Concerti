package com.concertportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CosaFacciamoC {

    @GetMapping("/cosaFacciamo")
    public String cosaFacciamo() {
        return "cosaFacciamo"; 
    }
}
