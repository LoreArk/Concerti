package com.concertportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concertportal.model.Concert;
import com.concertportal.service.ConcertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/reserved")
public class ReservedController {

    @Autowired
    private ConcertService concertService;

    private Map<String, String> errors;


    @GetMapping
    public String renderPage(Model model, @RequestParam(required = false) Integer id, @RequestParam(required = false) String result) {
        
        List<Concert> concerts = concertService.getAllConcerts();
        
        model.addAttribute("concerts", concerts);
        model.addAttribute("result", result);

        return "reserved";
    }
    
    @PostMapping
    public String formHandler(@RequestBody String entity) {
        
        

        return entity;
    }
    

}
