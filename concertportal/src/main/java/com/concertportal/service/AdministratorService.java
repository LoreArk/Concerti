package com.concertportal.service;

import com.concertportal.model.Administrator;

import jakarta.servlet.http.HttpSession;

public interface AdministratorService {
    String loginCheck(String email, String password, HttpSession session);
    Administrator adminData(Integer id);
    String emailCheck(String email);
}
