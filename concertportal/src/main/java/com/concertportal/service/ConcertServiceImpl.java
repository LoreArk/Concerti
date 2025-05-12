package com.concertportal.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.concertportal.model.Concert;
import com.concertportal.repository.ConcertRepository;

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
        throw new UnsupportedOperationException("Unimplemented method 'registerConcert'");
    }

    @Override
    public String deleteConcert(Integer id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteConcert'");
    }

    @Override
    public Object[] concertValidation(Concert concert, String concertName, String artist, LocalDateTime date,
            String genre, String description, Integer locationID) {
        
        

        throw new UnsupportedOperationException("Unimplemented method 'concertValidation'");
    }

}
