package com.example.entities;

import java.util.List;

import javax.persistence.OneToMany;

public class Teacher extends User {
	
	private User user;
	private String privelage;
	
	//@OneToMany
	private List<Course> courses;
	
	//@OneToMany
	private List<Exam> exams;
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getPrivelage() 
	{
		return privelage;
	}
	public void setPrivelage(String privelage) {
		this.privelage = privelage;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	public Teacher(String username, String password, User user, String privelage) {
		super(username, password);
		this.user = user;
		this.privelage = privelage;

	}
	public Teacher() {
		
	}
	
	

}
