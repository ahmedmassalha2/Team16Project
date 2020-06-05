package com.example.entities;

public class Principal {
	
	private User user;
	private String privelage;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPrivelage() {
		return privelage;
	}
	public void setPrivelage(String privelage) {
		this.privelage = privelage;
	}
	public Principal(User user, String privelage) {
		super();
		this.user = user;
		this.privelage = privelage;
	}

	public Principal() {
		
		
	}
	
	
	
	

}
