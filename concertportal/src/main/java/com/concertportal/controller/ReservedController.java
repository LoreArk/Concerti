package com.concertportal.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> formHandler(@RequestBody Concert receivedData) {
        System.out.println("FORM HANDLER");
        return concertService.registerConcert(receivedData);
    }

    // @PostMapping
    // @ResponseBody
    // public ResponseEntity<String> formHandler(
    //     @ModelAttribute Concert concertForm,  // Usa ModelAttribute per i dati
    //     @RequestParam("poster") MultipartFile poster,
    //     @RequestParam("artistPhoto") MultipartFile artistPhoto
    // ) {
    //     System.out.println("Form handler");
    //     System.out.println("Ricevuto concerto: " + concertForm.getArtist() + 
    //                     (poster != null && !poster.isEmpty() ? 
    //                     ", con immagine: " + poster.getOriginalFilename() : ", senza immagine"));
        
    //     try {
    //         // Chiama il servizio per registrare il concerto
    //         ResponseEntity<String> result = concertService.registerConcert(concertForm, poster, artistPhoto);
    //         return result;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body("Errore durante il salvataggio: " + e.getMessage());
    //     }
    // }
    
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
    
    // @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //     @ResponseBody
    //     public ResponseEntity<String> formHandler(
    //         @RequestParam("artist") String artist,
    //         @RequestParam("concertName") String concertName,
    //         @RequestParam("genre") String genre,
    //         @RequestParam("location.city.name") String cityName,
    //         @RequestParam("location.region.name") String regionName,
    //         @RequestParam("location.name") String locationName,
    //         @RequestParam("location.address") String address,
    //         @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    //         @RequestParam("time") String time,  // puoi anche parsarlo in LocalTime
    //         @RequestParam("poster") MultipartFile poster,
    //         @RequestParam("artistPhoto") MultipartFile artistPhoto
    //     ) {
    //         System.out.println("Artista: " + artist + ", poster: " + poster.getOriginalFilename());

    //         Concert concert = new Concert();
    //         concert.setArtist(artist);
    //         concert.setConcertName(concertName);
    //         concert.setGenre(genre);


    //          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //         LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, formatter);

    //         concert.setDate(dateTime);

    //         Location location = new Location();
    //         City city = new City();
    //         Region region = new Region();

    //         city.setName(cityName);
    //         region.setName(regionName);
    //         location.setCity(city);
    //         location.setRegion(region);
    //         location.setName(locationName);
    //         location.setAddress(address);

    //         concert.setLocation(location);

    //         return concertService.registerConcert(concert, poster, artistPhoto);
    //     }
}
