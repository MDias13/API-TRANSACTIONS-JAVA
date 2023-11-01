package com.banco.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.banco.dtos.ExceptionDTO;



@RestControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> UsuarioDuplicado(DataIntegrityViolationException exception) {
 
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuario ja cadastrado","400");
		
        
		return ResponseEntity.badRequest().body(exceptionDTO);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> ExceptionaGeral(Exception exception) {
 
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"500");
		
        
		return ResponseEntity.internalServerError().body(exceptionDTO);

}
}