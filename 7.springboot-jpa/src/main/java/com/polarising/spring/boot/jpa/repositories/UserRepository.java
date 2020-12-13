package com.polarising.spring.boot.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.polarising.spring.boot.jpa.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	List<User> findByRole(String role);
	
	
}
