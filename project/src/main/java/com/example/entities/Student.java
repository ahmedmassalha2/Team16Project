package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String privelage;
	
	
	@ManyToMany
	private List<Course> courses;

	
	@ManyToMany	
	private List<Exam> exams;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
	private List<checkedExam> grades;
	private String username;
	private String password;
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
	

	
	public Student(int id, String privelage, String username, String password) {
		super();
		this.id = id;
		this.privelage = privelage;
		this.username = username;
		this.password = password;
	}

	public Student() {
		
	}
	
	

}
