package com.concertportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.concertportal.model.Concert;

import jakarta.servlet.http.HttpSession;

public interface ConcertService {
    List<Concert> getAllConcerts();
    Concert getConcertById(Integer id);
   // ResponseEntity<String> registerConcert(Concert concert);
    ResponseEntity<String> registerConcert(Concert concert, MultipartFile poster, MultipartFile artistPhoto);
    String deleteConcert(Integer id, HttpSession session);
    Object[] concertValidation(Concert concert, String concertName, String artist, LocalDateTime date, String genre, String description, Integer locationID);
}