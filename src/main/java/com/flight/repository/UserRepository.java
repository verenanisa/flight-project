package com.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flight.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select count(p) = 1 from User p where email = ?1")
	public boolean findExistByEmail(String email);

	@Query("select p.password from User p where email = ?1")
	public String getPasswordByEmail(String email);

	@Query("select p from User p where email = ?1")
	public User getUser(String email);

}
