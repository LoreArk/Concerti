package com.concertportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.concertportal.model.Administrator;

public interface AdminRepository extends CrudRepository<Administrator, Integer> {
    Administrator findAdminByEmail(String email);
}
