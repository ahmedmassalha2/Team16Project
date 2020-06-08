package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String idNum;
	private String privelage;
	@JsonIgnore
	@ManyToMany()
	private List<Course> courses;


	@JsonIgnore
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

	public List<checkedExam> getGrades() {
		return grades;
	}

	public void setGrades(List<checkedExam> grades) {
		this.grades = grades;
	}

	public Student(String idNum, String privelage, String username, String password) {
		super();
		//this.id = id;
		this.idNum = idNum;
		this.privelage = privelage;
		this.username = username;
		this.password = password;
	}

	public Student() {

	}

}
