package com.example.entities;

import java.util.List;

import net.bytebuddy.asm.Advice.This;

public class checkedExam extends Exam {
	private Student student;
	private double grade;
	private String discreption;

	public checkedExam(Teacher teacher, Subject subject, List<Question> questions, String timeString, Student student,
			double grade, String discreption) {
		super(teacher, subject, questions, timeString);
		this.student = student;
		this.grade = grade;
		this.discreption = discreption;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getDiscreption() {
		return discreption;
	}

	public void setDiscreption(String discreption) {
		this.discreption = discreption;
	}

}
