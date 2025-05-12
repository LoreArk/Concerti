package com.concertportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.concertportal.model.Concert;

public interface ConcertService {
    List<Concert> getAllConcerts();
    Concert getConcertById(Integer id);
    ResponseEntity<String> registerConcert(Concert concert);
    String deleteConcert(Integer id);
    Object[] concertValidation(Concert concert, String concertName, String artist, LocalDateTime date, String genre, String description, Integer locationID);
}