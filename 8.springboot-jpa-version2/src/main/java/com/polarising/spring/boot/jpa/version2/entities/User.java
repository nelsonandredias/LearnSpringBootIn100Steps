package com.polarising.spring.boot.jpa.version2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//Table - User

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String role;
	
	
	//default constructor for JPA
	protected User() {
		super();
	}
	
	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
}
