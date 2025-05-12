package com.concertportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concertportal.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdministratorService adminService;

    @GetMapping()
    public String renderPage(@RequestParam(required = false) String error, Model model, HttpSession session) {
        if(session.getAttribute("admin") != null) {
            return "redirect:/reserved";
        }
        model.addAttribute("error", error);
        return "login";
    }
    
    @PostMapping()
    public String formHandler(@RequestParam String email, @RequestParam String password, HttpSession session) {
        
        System.out.println("Form handler");
        System.out.println("EMAIL: " + email);
        System.out.println("PASSWORD: " + password);
        
       String checkResult = adminService.loginCheck(email, password, session);
        if(checkResult.equals("Credenziali Errate")) {
            return "redirect:/login?error=" + checkResult;
        }
        return "redirect:/reserved";
    }
}
