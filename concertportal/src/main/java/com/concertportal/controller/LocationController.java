package com.concertportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concertportal.model.Location;
import com.concertportal.service.LocationService;

@RestController
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping("/locations")
    public List<Location> getLocationsByCityId(@RequestParam Integer id){
        return locationService.getAllLocationsById(id);
    }

}
