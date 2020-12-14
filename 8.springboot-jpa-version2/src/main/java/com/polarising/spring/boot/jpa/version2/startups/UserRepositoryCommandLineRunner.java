package com.polarising.spring.boot.jpa.version2.startups;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.spring.boot.jpa.version2.entities.User;
import com.polarising.spring.boot.jpa.version2.repositories.UserRepository;
import com.polarising.spring.boot.jpa.version2.services.daos.UserDAOService;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;

	private Optional<User> userWithIdOne;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Andre", "Admin");
		
		userRepository.save(user);
		
		userWithIdOne = userRepository.findById(1L);
		
		List<User> usersList = userRepository.findAll();
		
		log.info("New User is created : " + user);
		
		log.info("User is retrieved : " + userWithIdOne);
		
		log.info("All users : " + usersList);
		
	}

}
