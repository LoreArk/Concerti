package com.concertportal.service;

import java.util.List;

import com.concertportal.model.City;

public interface CityService {
    List<City> getAllCities();
    City getCityById(Integer id);
}
