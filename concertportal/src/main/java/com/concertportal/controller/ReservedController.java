package com.concertportal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.concertportal.model.City;
import com.concertportal.model.Concert;
import com.concertportal.model.Location;
import com.concertportal.model.Region;
import com.concertportal.repository.CityRepository;
import com.concertportal.service.CityService;
import com.concertportal.service.ConcertService;
import com.concertportal.service.LocationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/reserved")
public class ReservedController {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CityService cityService;

    private Map<String, String> errors;


    @GetMapping
    public String renderPage(HttpSession session, Model model, 
            @RequestParam(required = false) Integer id, 
                @RequestParam(required = false) String result,
                    @RequestParam(required = false) Integer cityId) {
        
        //impedisco accesso se la sessione non ha credenziali
        if(session.getAttribute("admin") == null) {
            return "redirect:/login";
        }
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

        List<Location> locations = new ArrayList<>();
        if(cityId != null) {
            locations = locationService.getAllLocationsById(cityId);
        }

        List<City> cities = cityService.getAllCities();


        System.out.println("CONCERT LOCATION ID:" + concertForm.getLocation().getId());
        
        for (Location location : locations) {
            System.out.println("LOCATION BY CITY: " + location.getName() + ", " + location.getId());
        }

        model.addAttribute("concertForm", concertForm);
        model.addAttribute("concerts", concerts);
        model.addAttribute("result", result);

        model.addAttribute("locations", locations);
        model.addAttribute("cities", cities);

        return "reserved";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> formHandler(@RequestBody Concert receivedData) {
        System.out.println("FORM HANDLER");
        return concertService.registerConcert(receivedData);
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
