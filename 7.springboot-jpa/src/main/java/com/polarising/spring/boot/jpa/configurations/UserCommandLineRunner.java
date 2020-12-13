package com.polarising.spring.boot.jpa.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.polarising.spring.boot.jpa.entities.User;
import com.polarising.spring.boot.jpa.repositories.UserRepository;

// commandlinerunner are invoked at startup time

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//create a few users at startup
		userRepository.save(new User("Nelson", "Admin"));
		userRepository.save(new User("Andre", "User"));
		userRepository.save(new User("John", "USer"));
		userRepository.save(new User("James", "Admin"));
		
		
		for(User user: userRepository.findAll()) {
			log.info(user.toString());
		}
		
		log.info("-------------------------------");
        log.info("Finding all Admins");
        log.info("-------------------------------");
        for (User admin : userRepository.findByRole("Admin")) {
            log.info(admin.toString());
            // Do something...
        }
	}

}
