package com.polarising.spring.boot.jpa.version2.startups;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.spring.boot.jpa.version2.entities.User;
import com.polarising.spring.boot.jpa.version2.services.daos.UserDAOService;

@Component
public class UserDAOCommandLineRunner implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserDAOCommandLineRunner.class);
	
	@Autowired
	private UserDAOService userDAOService;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Nelson", "Admin");
		
		long insertID = userDAOService.insertUser(user);
		
		log.info("New User is created : " + user);
	}

}
