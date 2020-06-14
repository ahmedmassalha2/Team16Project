package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
//import com.example.entities.Course;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String privelage;

	@JsonIgnore
	@ManyToMany()
	private List<Course> courses;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private List<Exam> exams;

	@JsonIgnore
	@ManyToMany()
	private List<Subject> subjects;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
	private List<todoItem> todoList;

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

	public void setCourses(Course courses) {
		this.courses.add(courses);
	}

	public void addCourses(Course... courses_) {
		for (Course course : courses_) {
			this.courses.add(course);
			course.getTeachers().add(this);
		}
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public void setSubjects(Subject subject) {
		this.subjects.add(subject);
	}

	public void addSubjects(Subject... subjects) {
		for (Subject subject : subjects) {
			this.subjects.add(subject);
			subject.getTeachers().add(this);
		}
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {

		this.exams = exams;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<todoItem> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<todoItem> todoList) {
		this.todoList = todoList;
	}
	public void addTodoItem(todoItem item) {
		todoList.add(item);
	}
	public Teacher(String privelage, String username, String password) {

		this.privelage = privelage;
		this.username = username;
		this.password = password;
		this.courses = new ArrayList<Course>();
		this.subjects = new ArrayList<Subject>();
		this.todoList = new ArrayList<todoItem>();
	}

	public Teacher() {

	}

}
