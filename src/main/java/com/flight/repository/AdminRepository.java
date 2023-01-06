package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.model.User;

public interface AdminRepository extends JpaRepository<User, Integer> {

}
