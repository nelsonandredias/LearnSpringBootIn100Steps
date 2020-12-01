package com.polarising.spring.web2.webapplication.Services;

import org.springframework.stereotype.Service;

import com.polarising.spring.web2.webapplication.Interfaces.ValidateCredentials;

@Service
public class LoginService implements ValidateCredentials{
	
	public boolean validateUser(String userName, String userPassword) {
		
		//only valid credentials (userName = admin, userPassword = pass)
		return userName.equalsIgnoreCase("admin") && userPassword.equalsIgnoreCase("pass");
	}
	

}
