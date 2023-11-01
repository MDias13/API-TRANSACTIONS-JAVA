package com.banco.domain.user;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.banco.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name="users")
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="documento")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	public User(UserDTO data) {
		
		this.nomeCompleto = data.nomeCompleto();
		this.documento = data.documento();
		this.email = data.email();
		this.senha = data.senha();
		this.balance = data.balance();
		this.userType = data.userType();
	}


	
	private String nomeCompleto;
	@Id
	@Column(unique = true)
	private String documento;
	
	@Column(unique = true)
	private String email;
	
	private String senha;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING) 
	private UserType userType;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userType == UserType.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_CLIENTE"));
		}else {
			return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
		}
		
		
				
	}			

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
	
}
