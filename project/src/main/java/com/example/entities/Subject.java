package com.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private int id;
	private String name;
	private String Snumber;
	private List<Question> questions;

	public Subject(String name, String Snumber) {
		this.name = name;
		this.Snumber = Snumber;
		this.questions = new ArrayList<Question>();
	}

	public Subject() {

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

	public String getSnumber() {
		return Snumber;
	}

	public void setSnumber(String snumber) {
		Snumber = snumber;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
