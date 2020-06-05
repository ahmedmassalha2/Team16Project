package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

public class Student extends User {
	
	private User user;
	private String privelage;
	/*
	@ManyToMany(mappedBy = "studentList", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
			targetEntity = Course.class)
	*/
	private List<Course> courses;
	
	/*
	@ManyToMany(mappedBy = "studentList", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
			targetEntity = Exam.class)
	*/
	private List<Exam> exams;
	
	//@OneToMany
	private List<checkedExam> grades;
	
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
	public List<checkedExam> getGrades() {
		return grades;
	}
	public void setGrades(List<checkedExam> grades) {
		this.grades = grades;
	}
	public Student(String username, String password, User user, String privelage) {
		super(username, password);
		this.user = user;
		this.privelage = privelage;
		
		

	}
	
	public Student() {
		
	}
	
	

}
