package com.polarising.spring.boot.jpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.polarising.spring.boot.jpa.entities.User;

// it will map the resource to the path "/users"

@RepositoryRestResource(path="users", collectionResourceRel = "users")
public interface UserRESTRepository extends PagingAndSortingRepository<User, Long>{

	List<User> findByRole(@Param("role") String role);
	
	
}
