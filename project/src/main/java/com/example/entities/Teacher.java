package com.example.entities;

import java.util.List;

import javax.persistence.CascadeType;
//import com.example.entities.Course;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Teacher")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String privelage;
	
	@ManyToMany
	@JoinTable(name="Courses_Teachers",
			joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
	private List<Course> courses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private List<Exam> exams;
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
	public void setCourses(Course courses) {
		this.courses.add(courses);
	}
	public List<Exam> getExams() {
		return exams;
	}
	public void setExams(List<Exam> exams) {
		
		this.exams = exams;
	}

	public Teacher(int id, String privelage, String username, String password) {
		super();
		this.id = id;
		this.privelage = privelage;
		this.username = username;
		this.password = password;
	}

	public Teacher() {
		
	}
	
	

}
