package com.concertportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concertportal.model.Administrator;
import com.concertportal.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdministratorServiceImpl implements AdministratorService{

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public String loginCheck(String email, String password, HttpSession session) {
        Administrator admin = adminRepository.findByEmail(email);
        
        if (admin == null) {
            return "Credenziali Errate";
        }
    
        if (!admin.getPassword().equals(password)) {
            return "Credenziali Errate";
        }

        session.setAttribute("admin", admin);
        return "Login Autorizzato";
    }

    @Override
    public Administrator adminData(Integer id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public String emailCheck(String email) {
        if(adminRepository.findByEmail(email) != null)
        {
            return "Email già registrata";
        }
        return null;
    }

}
