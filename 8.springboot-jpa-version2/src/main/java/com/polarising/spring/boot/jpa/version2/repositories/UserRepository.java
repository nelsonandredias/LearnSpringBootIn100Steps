package com.polarising.spring.boot.jpa.version2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.polarising.spring.boot.jpa.version2.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
