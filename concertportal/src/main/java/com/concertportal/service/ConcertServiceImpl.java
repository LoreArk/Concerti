package com.concertportal.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

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

        System.out.println("REGISTER CONCERT");
        System.out.println(concert.getId());
        System.out.println(concert.getConcertName());
        System.out.println(concert.getLocation().getId());
        System.out.println(concert.getLocation().getCity().getId());

        

        //modify
        if(concert.getId() != null) {
            System.out.println("MODIFY, ID: " + concert.getId());
            Concert concertMod = concertRepository.findById(concert.getId()).get();
            BeanUtils.copyProperties(concert, concertMod, "id");
            concertRepository.save(concertMod);
            return ResponseEntity.ok("Operation completed");
        }

        concertRepository.save(concert);
        return ResponseEntity.ok("Operation completed");
    }

    // @Override
    // public ResponseEntity<String> registerConcert(Concert concert, MultipartFile poster, MultipartFile artistPhoto) {
    //       try {

    //         if (concert.getId() != null) {
    //             System.out.println("MODIFYING EXISTING CONCERT");
    //             Concert existingConcert = concertRepository.findById(concert.getId())
    //                     .orElseThrow(() -> new IllegalArgumentException("Concert not found with id: " + concert.getId()));

    //             BeanUtils.copyProperties(concert, existingConcert, "id", "poster", "foto");

    //             // if (poster != null && !poster.isEmpty()) {
    //             //     String posterPath = saveFileFromMultipart(poster, "uploads/images");
    //             //     existingConcert.setPoster(posterPath);
    //             // }

    //             // if (artistPhoto != null && !artistPhoto.isEmpty()) {
    //             //     String artistPhotoPath = saveFileFromMultipart(artistPhoto, "uploads/images");
    //             //     existingConcert.setFoto(artistPhotoPath);
    //             // }

    //             concertRepository.save(existingConcert);
                
    //         } else {
    //             System.out.println("INSERTING NEW CONCERT");

    //             // if (poster != null && !poster.isEmpty()) {
    //             //     String posterPath = saveFileFromMultipart(poster, "uploads/images");
    //             //     concert.setPoster(posterPath);
    //             // }

    //             // if (artistPhoto != null && !artistPhoto.isEmpty()) {
    //             //     String artistPhotoPath = saveFileFromMultipart(artistPhoto, "uploads/images");
    //             //     concert.setFoto(artistPhotoPath);
    //             // }

    //             concertRepository.save(concert);
    //         }

    //         return ResponseEntity.ok("Operazione completata con successo");

    //     }catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("Errore generico: " + e.getMessage());
    //     }
    // }

    
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
