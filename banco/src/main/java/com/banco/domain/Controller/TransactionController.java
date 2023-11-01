package com.banco.domain.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.domain.transaction.transactions;
import com.banco.dtos.TransactionDTO;
import com.banco.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	
	@PostMapping
    public ResponseEntity<transactions> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception{
        
 
    	transactions createdTransaction = transactionService.createTransaction(transactionDTO);
    	
    	
        
    	return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    	
    }

    @GetMapping
    public ResponseEntity<List<transactions>> getAllTransactions() {
        List<transactions> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
    
    
    
}
