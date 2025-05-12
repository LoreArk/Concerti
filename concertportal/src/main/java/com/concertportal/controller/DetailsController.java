package com.concertportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concertportal.model.Concert;
import com.concertportal.service.ConcertService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    private ConcertService concertService;  

    @GetMapping
    public String renderPage(@RequestParam(defaultValue = "0") Integer id, Model model, HttpSession session, @RequestParam(required = false) String result) {
        Concert concert = concertService.getConcertById(id);
        if(concert == null) {
            return "redirect:/";
        }

        System.out.println("Artista: " + concert.getArtist());
        System.out.println("Data: " + concert.getDate());
        System.out.println("Venue: " + concert.getLocation().getName());
        System.out.println("Region: " + 
            (concert.getLocation().getRegion() != null ? concert.getLocation().getRegion().getName() : "null"));
        System.out.println("Città: " + 
            (concert.getLocation().getCity() != null ? concert.getLocation().getCity().getName() : "null"));
        
        System.out.println("Address: " + concert.getLocation().getAddress());
        System.out.println("---");

        model.addAttribute("concert", concert);
        model.addAttribute("result", result);
        return "details";
    }
}
