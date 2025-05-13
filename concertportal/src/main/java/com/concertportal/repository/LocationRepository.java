package com.concertportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concertportal.model.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    List<Location> findByCityId(Integer ID);
}
