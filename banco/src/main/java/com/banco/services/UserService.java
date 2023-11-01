package com.banco.services;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.banco.domain.repository.UserRepository;
import com.banco.domain.user.User;
import com.banco.dtos.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User enviado,BigDecimal valor) throws Exception {
		
		
	}
	
	public User findByDocumento(String documento) throws Exception {
		
		
		
		return this.repository.findByDocumento(documento).orElseThrow(() -> new Exception("Usuario não encontrado"));
		
		
	}
	
	
	public void saveUser(User user) {
		
		this.repository.save(user);
	}
	
	
	public User createUser(UserDTO data) {
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
		
		User newUser = new User(data);
		
		newUser.setSenha(encryptedPassword);
		
		this.saveUser(newUser);
		return newUser;
		
			
	}
	
	public List<User> getAllUsers() {
        
		return repository.findAll();
    }
	
	
	
}
