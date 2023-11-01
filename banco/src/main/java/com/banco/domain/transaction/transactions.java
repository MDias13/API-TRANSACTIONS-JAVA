package com.banco.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.banco.domain.user.User;
import com.banco.dtos.TransactionDTO;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="transactions")
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class transactions {
	
	
   public transactions(TransactionDTO data) {
		
		this.valor = data.valor();
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name="enviado_documento")
	private User enviado;
	
	@ManyToOne
	@JoinColumn(name="recebido_documento")
	private User recebido;
	
	private LocalDateTime timestamp;
	

}
