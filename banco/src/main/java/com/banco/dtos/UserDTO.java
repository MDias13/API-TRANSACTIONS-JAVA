package com.banco.dtos;

import java.math.BigDecimal;

import com.banco.domain.user.UserType;

public record UserDTO(String nomeCompleto,String documento,BigDecimal balance,String email,String senha,UserType userType) {

}
