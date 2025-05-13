package com.concertportal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.concertportal.model.Location;
import com.concertportal.repository.LocationRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public ResponseEntity<String> registerLocation(Location concert) {
        
        throw new UnsupportedOperationException("Unimplemented method 'registerLocation'");
    }



}
