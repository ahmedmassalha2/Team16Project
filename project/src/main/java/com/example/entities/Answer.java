package com.example.entities;

public class Answer {
	private int id;
	private String anString;
	private Question question;
	private boolean is_right;

	public Answer(String anString, Question question, boolean is_right) {
		this.anString = anString;
		this.question = question;
		this.is_right = is_right;
	}

	public Answer(String anString, boolean is_right) {
		super();
		this.anString = anString;
		this.is_right = is_right;
	}

	public Answer() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnString() {
		return anString;
	}

	public void setAnString(String anString) {
		this.anString = anString;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isIs_right() {
		return is_right;
	}

	public void setIs_right(boolean is_right) {
		this.is_right = is_right;
	}
	

}
