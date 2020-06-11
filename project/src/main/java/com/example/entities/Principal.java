package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Principal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String privelage;

	private String username;
	private String password;

	public Principal(String privelage, String username, String password) {
		this.privelage = privelage;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrivelage() {
		return privelage;
	}

	public void setPrivelage(String privelage) {
		this.privelage = privelage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Principal() {

	}

}
