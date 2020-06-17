package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Exam;
import com.example.entities.Question;
import com.example.entities.Subject;
import com.example.entities.Teacher;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExamOps {

	public static String getExamsList() throws JsonProcessingException {

		dataBase.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		List<String> examsdisc = new ArrayList<String>();
		for (Exam exam : dataBase.getAll(Exam.class)) {

			String discString = "Exam id: " + exam.getId() + "\nExam in " + exam.getSubject().getName() + " writen by "
					+ exam.getTeacher().getUsername() + "\nDuration: " + exam.getTimeString() + " hours";
			examsdisc.add(discString);
		}
		dataBase.closeSess();
		return mapper.writeValueAsString(examsdisc);
	}
	public static String getSubjectName() throws JsonProcessingException{
		dataBase.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		List<String> subjectsNames = new ArrayList<String>();
		subjectsNames.add("ALL");
		for (Subject t : dataBase.getAll(Subject.class)) {

			String discString = "" + t.getName();
			subjectsNames.add(discString);
		}
		System.out.println("Subjects: " + subjectsNames);
		dataBase.closeSess();
		return mapper.writeValueAsString(subjectsNames);
	}
	

	public static String getExamsBySubject(String subject) throws JsonProcessingException {

		System.out.println("Subject: "+subject);
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where subjectName = :subjectName");
		query.setParameter("subjectName", subject);
		List<Exam> exams = query.getResultList();
		List<String> examsdisc = new ArrayList<String>();
		List list = query.list();
		if (list.size() != 0) {
			System.out.println("Entered exam subject");
		//Exam teacher = (Exam) query.getSingleResult();
			//List<String> questions = new ArrayList<String>();
			for (Exam exam : exams) {

				String discString = "Exam id: " + exam.getId() + "\nExam in " + exam.getSubject().getName()
						+ " writen by " + exam.getTeacher().getUsername() + "\nDuration: " + exam.getTimeString()
						+ " hours";
				examsdisc.add(discString);
			}
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(examsdisc);
			System.out.println("JSON = " + json);
			session.close();
			return json;
		}

		session.close();
		return "";
	}

}
