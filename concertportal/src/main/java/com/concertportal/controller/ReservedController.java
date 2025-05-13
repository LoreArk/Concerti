package com.concertportal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    // @PostMapping
    // @ResponseBody
    // public ResponseEntity<String> formHandler(@RequestBody Concert receivedData) {
    //     System.out.println("FORM HANDLER");
    //     return concertService.registerConcert(receivedData);
    // }

    @PostMapping("/reserved")
    @ResponseBody
    public ResponseEntity<String> formHandler(
        @ModelAttribute Concert concertForm,  // Usa ModelAttribute per i dati
        @RequestParam("poster") MultipartFile poster,
        @RequestParam("artistPhoto") MultipartFile artistPhoto
    ) {
        System.out.println("Ricevuto concerto: " + concertForm.getArtist() + ", con immagine: " + poster.getOriginalFilename());
        
        // Salva il concerto nel database (se necessario)
        concertService.registerConcert(concertForm, poster, artistPhoto);

        return ResponseEntity.ok("Operazione Eseguita");
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
