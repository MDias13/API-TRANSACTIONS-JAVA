package com.banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banco.domain.user.User;
import com.banco.dtos.NotificationDTO;

@Service
public class NotificationService {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public void enviadoNotification(User user, String message) throws Exception {
		
		String email = user.getEmail();
		NotificationDTO notificationrequest = new NotificationDTO(email,message);
		
		
		
		ResponseEntity<String> notificationResponse = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", notificationrequest, String.class); 
		
		if(notificationResponse.getStatusCode() == HttpStatus.OK) {
			
			System.out.println("erro ao enviar a notificação");
			
			throw new Exception("Serviço fora do ar");
			
			
			
			
			
		}
		
		
		
	}

}
