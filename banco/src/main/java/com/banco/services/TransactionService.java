package com.banco.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banco.domain.repository.TransactionRepository;
import com.banco.domain.transaction.transactions;
import com.banco.domain.user.User;
import com.banco.dtos.TransactionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;





@Service
public class TransactionService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<transactions> getAllTransactions() {
        
		return repository.findAll();
    }
	
	
	public transactions createTransaction(TransactionDTO transaction) throws Exception {
	   
		User enviado = this.userService.findByDocumento(transaction.enviado_documento());
		
		User recebido = this.userService.findByDocumento(transaction.recebido_documento());
		
		
		
		
		userService.validateTransaction(enviado,transaction.valor());
		
		
		
		
		
		
	
		
		transactions transaction1 = new transactions();
		
		transaction1.setValor(transaction.valor());
		transaction1.setEnviado(enviado);
		transaction1.setRecebido(recebido);
		transaction1.setTimestamp(LocalDateTime.now());
		
		enviado.setBalance(enviado.getBalance().subtract(transaction.valor()));
		
		recebido.setBalance(recebido.getBalance().add(transaction.valor()));
		
		
		this.repository.save(transaction1);
		
		this.userService.saveUser(enviado);
		this.userService.saveUser(recebido);
		
		return transaction1;
		
		
		
	}
	
	public boolean authorizeTransaction(User enviado,BigDecimal valor) throws JsonMappingException, JsonProcessingException {
		
		ResponseEntity<String> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", String.class);
		
		if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String responseBody = authorizationResponse.getBody();
			
			ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String message = jsonNode.get("message").asText();
            
            return "Autorizado".equalsIgnoreCase(message);
        } else {
     
            return false;
        }
			
			
		}
            
}












