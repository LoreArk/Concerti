
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
import org.springframework.web.bind.annotation.PathVariable;
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

// localhost:8080/modify
@Controller
@RequestMapping("/modify")
public class ModifyController {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CityService cityService;

    private Map<String, String> errors;

    @GetMapping("/modify/{id}")
    public String showModifyPage(@PathVariable Integer id, Model model) {
        Concert concert = concertService.getConcertById(id);
        if (concert == null) {
            return "redirect:/reserved?result=notfound"; // Reindirizza a una pagina di errore
        }
        model.addAttribute("concert", concert);
        return "modify";
    }

    @PostMapping("/update")
    public String updateConcert(HttpSession session, Concert concertForm) {
        // Controllo accesso admin
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        try {
            // Aggiorna il concerto nel database
            concertService.updateConcert(concertForm, session);
            return "redirect:/reserved?result=success";
        } catch (Exception e) {
            // Gestione errori
            return "redirect:/modify?id=" + concertForm.getId() + "&result=error";
        }
    }

    @GetMapping("/locations")
    @ResponseBody
    public List<Location> getLocationsByCityId(@RequestParam Integer cityId) {
        return locationService.getAllLocationsById(cityId);
    }

    @PostMapping("/delete")
    public String deleteConcert(HttpSession session, @RequestParam Integer id) {
        // Controllo accesso admin
        if (session.getAttribute("admin") == null) {
            return "redirect:/login";
        }

        try {
            concertService.deleteConcert(id, session);
            return "redirect:/reserved?result=deleted";
        } catch (Exception e) {
            return "redirect:/modify?result=deleteerror";
        }
    }
}