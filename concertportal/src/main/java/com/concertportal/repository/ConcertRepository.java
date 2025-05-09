package com.concertportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.concertportal.model.Concert;

public interface ConcertRepository extends CrudRepository<Concert, Integer>  {
    List<Concert> findAllByOrderByDateAsc();
}
