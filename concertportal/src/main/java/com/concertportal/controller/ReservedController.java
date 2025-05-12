package com.concertportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concertportal.model.City;
import com.concertportal.model.Concert;
import com.concertportal.model.Location;
import com.concertportal.model.Region;
import com.concertportal.service.ConcertService;

import jakarta.servlet.http.HttpSession;

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
        
        Concert concertForm;
        if (id == null) {
            concertForm = new Concert();
            concertForm.setLocation(new Location());
            concertForm.getLocation().setCity(new City());
            concertForm.getLocation().setRegion(new Region());
        } else {
            concertForm = concertService.getConcertById(id);
        }

        model.addAttribute("concertForm", concertForm);
        model.addAttribute("concerts", concerts);
        model.addAttribute("result", result);

        return "reserved";
    }

    @PostMapping
    public String formHandler(@RequestBody String entity) {
        
        return entity;
    }
    
    @GetMapping("/delete")
    public String deleteConcert(@RequestParam Integer id, HttpSession session) {
        concertService.deleteConcert(id, session);
        return "redirect:/reserved";
    }
    
    
    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        
        session.removeAttribute("admin");
        return "redirect:/";
    }
}
