package com.example.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String discription;
	
	@Column(name = "question_number")
	private String number;
	private String subjectNumber;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "question")
	private List<Answer> answers;
	
	@ManyToMany
	@JoinTable(
			name="Question_Exam",
			joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "exam_id", referencedColumnName = "id")
	)
	private List<Exam> exams;
	
	@ManyToMany
	@JoinTable(
			name="Question_CheckedExam",
			joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "checkedexam_id", referencedColumnName = "id")
	)
	private List<checkedExam> checkedExams;
	public Question(String discription, String number, Subject subject) {
		this.discription = discription;
		this.subject = subject;
		this.number = number;
		this.subjectNumber = subject.getSnumber();
		this.answers = new ArrayList<Answer>();
		this.subject.getQuestions().add(this);
		this.checkedExams = new ArrayList<checkedExam>();
		this.exams = new ArrayList<Exam>();
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

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	public List<checkedExam> getCheckedExams() {
		return checkedExams;
	}

	public void setCheckedExams(List<checkedExam> checkedExams) {
		this.checkedExams = checkedExams;
	}
	public void addExams(Exam... examsLst) {
		for (Exam ga : examsLst) {
			exams.add(ga);
			ga.getQuestions().add(this); 
		}
	}
	public void addExams(checkedExam... examsLst) {
		for (checkedExam ga : examsLst) {
			checkedExams.add(ga);
			ga.getQuestions().add(this); 
		}
	}
}
