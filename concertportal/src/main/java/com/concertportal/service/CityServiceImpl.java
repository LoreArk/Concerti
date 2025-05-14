package com.concertportal.service;

import java.util.List;
import com.concertportal.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concertportal.model.City;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    @Override
    public City getCityById(Integer id) {
        return (City) cityRepository.findById(id).orElse(null);
    }

}
