package com.example.entities;

import java.util.ArrayList;
import java.util.List;




public class Question {
	private int id;
	private String discription;
	private String number;
	private String subjectNumber;
	private Subject subject;
	private List<Answer> answers;

	public Question(String discription, String number, Subject subject) {
		this.discription = discription;
		this.subject = subject;
		this.number = number;
		this.subjectNumber = subject.getSnumber();
		this.answers = new ArrayList<Answer>();
		this.subject.getQuestions().add(this);
	}

	public Question() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
