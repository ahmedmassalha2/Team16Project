package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Course;
import com.example.entities.Exam;
import com.example.entities.Question;
import com.example.entities.Subject;
import com.example.entities.Teacher;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class teacherOps {
	public static String getExams(String user, String paString) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();
		if (list.size() != 0) {
			Teacher teacher = (Teacher) query.getSingleResult();
			List<Exam> l = teacher.getExams();
			List<String> examsdisc = new ArrayList<String>();
			for (Exam exam : l) {

				String discString = "Exam id: " + exam.getId() + "\nExam in " + exam.getSubject().getName()
						+ " writen by " + exam.getTeacher().getUsername() + "\nDuration: " + exam.getTimeString()
						+ " hours";
				examsdisc.add(discString);
			}
			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(examsdisc);
			System.out.println("JSON = " + json);
			return json;

		}
		return "";
	}

	public static String getQuestions(String user, String paString) throws JsonProcessingException {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();
		if (list.size() != 0) {
			Teacher teacher = (Teacher) query.getSingleResult();
			List<String> questions = new ArrayList<String>();
			for (Subject subject : teacher.getSubjects()) {
				String suString = subject.getSnumber();
				query = session.createQuery("from Question where subjectNumber = :subjectNumber");
				query.setParameter("subjectNumber", suString);
				list = query.list();
				for (int i = 0; i < list.size(); i++) {
					Question question = (Question) list.get(i);
					System.out.println(question.getDiscription());
					String queString = "Id: " + question.getId() + "\n" + question.getDiscription();
					questions.add(queString);
				}

			}
			if (!questions.isEmpty()) {
				ObjectMapper mapper = new ObjectMapper();

				String json = mapper.writeValueAsString(questions);
				System.out.println("JSON = " + json);
				return json;
			}
			return "";
		}

		return "";
	}

}
