package com.banco.domain.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.domain.user.User;
import com.banco.dtos.AuthenticationDTO;
import com.banco.infra.TokenService;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenservice;
	

	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationDTO data) {
        
	var usernamepassword = new UsernamePasswordAuthenticationToken(data.email(),data.senha());
	
	var auth = this.authenticationManager.authenticate(usernamepassword);
	
	var token = tokenservice.generateToken((User) auth.getPrincipal());
	
	
	
	return ResponseEntity.ok(token);
       
    }

}
