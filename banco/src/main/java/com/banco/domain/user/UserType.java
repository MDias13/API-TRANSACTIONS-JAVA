package com.banco.domain.user;

public enum UserType {
	
	CLIENTE("cliente"),
	ADMIN("admin");
	
	
	private String role;
	
	
	UserType(String role){
		this.role = role;
	}


	public String getRole() {
		return role;
	}
	
	
	

}
