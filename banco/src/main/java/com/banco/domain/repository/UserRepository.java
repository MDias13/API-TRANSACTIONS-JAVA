package com.banco.domain.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.banco.domain.user.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByDocumento(String documento);
	
	
	UserDetails findByEmail(String email);
	
	
	
}
