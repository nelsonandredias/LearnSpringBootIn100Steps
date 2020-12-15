package com.polarising.spring.web2.webapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polarising.spring.web2.webapplication.models.Todo;


public interface TodoRepository extends JpaRepository<Todo, Integer> {

	// custom query to retrieve all todos of a given user
	List<Todo> findByUser(String user);
	
}
