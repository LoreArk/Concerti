package com.concertportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/details")

public class DetailsController {

    @Autowired
    private DetailsService detailsService;  

    @GetMapping
    public String renderPage(@RequestParam(defaultValue = 0) String param) {
        Libro libro = libroService.datiLibro(id);
    if(concert == null) {
      return "redirect:/";
    }
    model.addAttribute("concert", );
    model.addAttribute("login", session.getAttribute("cliente") != null); // true se loggato o false se sloggato
    model.addAttribute("esito", esito); // null se ingresso da home page o valore variabile se aggiunto libro
    return "dettaglio";
        return new String();
    }
    

}
