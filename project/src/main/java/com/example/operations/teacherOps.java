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
import com.example.entities.todoItem;
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
			session.close();
			return json;

		}
		session.close();
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
				session.close();
				return json;
			}
			session.close();
			return "";
		}

		return "";
	}

	public static String getQuestionsSubject(String user, String paString, String subject)
			throws JsonProcessingException {

		Teacher teacher = getTeacher(user, paString);
		dataBase.getInstance();
		Session session = dataBase.getSession();
		for (Subject s : teacher.getSubjects()) {
			if (s.getName().equals(subject)) {

				Query query = session.createQuery("from Question where subjectNumber = :subjectNumber");
				query.setParameter("subjectNumber", s.getSnumber());
				List list = query.list();

				if (list.size() != 0) {
					List<String> questions = new ArrayList<String>();
					for (int i = 0; i < list.size(); i++) {
						Question question = (Question) list.get(i);
						System.out.println(question.getDiscription());
						String queString = "Id: " + question.getId() + "\n" + question.getDiscription();
						questions.add(queString);
					}
					if (!questions.isEmpty()) {
						ObjectMapper mapper = new ObjectMapper();

						String json = mapper.writeValueAsString(questions);
						System.out.println("JSON = " + json);
						session.close();
						return json;
					}
					session.close();
					return "";
				}
			}
		}

		return "";
	}

	public static String getTeacherSubjects(String user, String paString) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();

		if (list.size() != 0) {
			Teacher teacher = (Teacher) query.getSingleResult();
			ObjectMapper mapper = new ObjectMapper();
			List<String> subjs = new ArrayList<String>();
			subjs.add("All");
			for (Subject s : teacher.getSubjects()) {
				subjs.add(s.getName());
			}
			String json = mapper.writeValueAsString(subjs);
			System.out.println("JSON = " + json);
			session.close();
			return json;
		}
		session.close();
		return null;
	}

	public static Teacher getTeacher(String user, String paString) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();

		if (list.size() != 0) {
			Teacher teacher = (Teacher) query.getSingleResult();
			return teacher;
		}
		return null;
	}

	public static String getToDo(String user, String paString) throws JsonProcessingException {
		Teacher teacher = getTeacher(user, paString);
		System.out.println(teacher.getUsername());
		List<todoItem> items = teacher.getTodoList();
		List<String> it = new ArrayList<String>();
		for (todoItem i : items) {
			System.out.println(i.getTodoString());
			it.add(i.getTodoString());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(it);
		dataBase.closeSess();
		System.out.println("JSON = " + json);
		return json;
	}

	public static String addToDo(String user, String paString, String item) throws JsonProcessingException {
		Teacher teacher = getTeacher(user, paString);
		Session session = dataBase.getSession();
		todoItem todo = new todoItem(item, false);
		session.save(todo);
		todo.setTeacher(teacher);
		teacher.addTodoItem(todo);
		session.update(teacher);
		session.getTransaction().commit();
		session.close();

		return "added";
	}
	public static String DellToDo(String user, String paString, String item) throws JsonProcessingException {
		Teacher teacher = getTeacher(user, paString);
		Session session = dataBase.getSession();
		int i=0;
		for(todoItem it:teacher.getTodoList()) {
			if(it.getTodoString().equals(item)) {
				teacher.getTodoList().remove(i);
				break;
			}
			i++;
		}
		session.update(teacher);
		session.getTransaction().commit();
		session.close();

		return "removed";
	}
}
