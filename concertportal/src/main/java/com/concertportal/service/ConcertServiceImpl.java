package com.concertportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.concertportal.model.Concert;
import com.concertportal.repository.ConcertRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ConcertServiceImpl implements ConcertService {

    @Autowired
    private ConcertRepository concertRepository;
    

    @Override
    public List<Concert> getAllConcerts() {
        return (List<Concert>) concertRepository.findAllByOrderByDateAsc();
    }

    @Override
    public Concert getConcertById(Integer id) {
        return concertRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> registerConcert(Concert concert) {

        //modify
        if(concert.getId() != null) {
            Concert concertMod = concertRepository.findById(concert.getId()).get();
            BeanUtils.copyProperties(concert, concertMod, "id");
            concertRepository.save(concertMod);
            return ResponseEntity.ok("Operation completed");
        }

        concertRepository.save(concert);
        return ResponseEntity.ok("Operation completed");
    }

    @Override
    public String deleteConcert(Integer id, HttpSession session) {

        Concert concert = getConcertById(id);
        concertRepository.delete(concert);
        return null;
    }

    @Override
    public Object[] concertValidation(Concert concert, String concertName, String artist, LocalDateTime date,
            String genre, String description, Integer locationID) {
        
        

        throw new UnsupportedOperationException("Unimplemented method 'concertValidation'");
    }

}
