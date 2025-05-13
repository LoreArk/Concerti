package com.concertportal.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.concertportal.model.Location;

public interface LocationService {
    List<Location> getAllLocations();
    Location getLocationById(Integer id);
    ResponseEntity<String> registerLocation(Location concert);
   // ResponseEntity<String> registerConcert(Concert concert, MultipartFile poster, MultipartFile artistPhoto);
    //Object[] concertValidation(Concert concert, String concertName, String artist, LocalDateTime date, String genre, String description, Integer locationID);
}
