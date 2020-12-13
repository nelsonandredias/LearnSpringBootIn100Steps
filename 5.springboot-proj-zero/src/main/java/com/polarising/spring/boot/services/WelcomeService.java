package com.polarising.spring.boot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

	//use property value
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	
	public String retrieveWelcomeMessage() {
		return welcomeMessage;
	}
	
}
