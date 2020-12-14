package com.polarising.spring.boot.jpa.version2.services.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.polarising.spring.boot.jpa.version2.entities.User;

@Repository
@Transactional
public class UserDAOService {

	//EntityManager allow us to save and manage data in database
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insertUser(User user) {
		
		entityManager.persist(user);

		return user.getId();
	}
	
}
