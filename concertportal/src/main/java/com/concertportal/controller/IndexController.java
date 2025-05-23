package com.concertportal.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concertportal.model.Concert;
import com.concertportal.service.ConcertService;

// localhost:8080
@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private ConcertService concertService;

    @GetMapping
    public String renderPage(Model model) {

        List<Concert> concerti = concertService.getAllConcerts();

        // DEBUG: stampa in console i concerti
        concerti.forEach(concerto -> {
            System.out.println("Artista: " + concerto.getArtist());
            System.out.println("Data: " + concerto.getDate());
            System.out.println("Venue: " + concerto.getLocation().getName());
            System.out.println("Region: " + 
                (concerto.getLocation().getRegion() != null ? concerto.getLocation().getRegion().getName() : "null"));
            System.out.println("Città: " + 
                (concerto.getLocation().getCity() != null ? concerto.getLocation().getCity().getName() : "null"));
            
            System.out.println("Address: " + concerto.getLocation().getAddress());
            System.out.println("Prezzo: " + concerto.getPrice());
            System.out.println("---");
            
        });

        model.addAttribute("concerti", concerti);
        return "index";
    }
}