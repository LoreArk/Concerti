package com.concertportal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    // @Override
    // public ResponseEntity<String> registerConcert(Concert concert) {

    //     //modify
    //     if(concert.getId() != null) {
    //         System.out.println("MODIFY, ID: " + concert.getId());
    //         Concert concertMod = concertRepository.findById(concert.getId()).get();
    //         BeanUtils.copyProperties(concert, concertMod, "id");
    //         concertRepository.save(concertMod);
    //         return ResponseEntity.ok("Operation completed");
    //     }

    //     concertRepository.save(concert);
    //     return ResponseEntity.ok("Operation completed");
    // }


     @Override
    public ResponseEntity<String> registerConcert(Concert concert, MultipartFile poster, MultipartFile artistPhoto) {

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


    private String saveFile(MultipartFile file, String folder) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename(); // Evita nomi duplicati
        Path uploadPath = Paths.get(folder);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath.toFile()); 

        return "../resources/static/images/" + fileName;
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
