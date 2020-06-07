package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
	private int id;
	private String name;
	private String Cnumber;
	

	@ManyToMany
	private List<Teacher> teachers;
	
	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	private List<Exam> exams;
	private List<checkedExam> checked_Exams;

	public Course(String name, String cnumber) {
		this.name = name;
		Cnumber = cnumber;
		this.exams = new ArrayList<Exam>();
		this.checked_Exams = new ArrayList<checkedExam>();
	}

	public Course() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnumber() {
		return Cnumber;
	}

	public void setCnumber(String cnumber) {
		Cnumber = cnumber;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public List<checkedExam> getChecked_Exams() {
		return checked_Exams;
	}

	public void setChecked_Exams(List<checkedExam> checked_Exams) {
		this.checked_Exams = checked_Exams;
	}

}
