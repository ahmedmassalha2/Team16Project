package com.example.operations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.ServerClientEntities.commandRunner;
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
			/*
			 * for (Exam exam : l) {
			 * 
			 * String discString = "Exam id: " + exam.getId() + "\nExam in " +
			 * exam.getSubject().getName() + " writen by " + exam.getTeacher().getUsername()
			 * + "\nDuration: " + exam.getTimeString() + " hours";
			 * examsdisc.add(discString); }
			 */
			for (Course course : teacher.getCourses()) {
				for (Exam exam : course.getExams()) {
					String discString = "Exam id: " + exam.getId() + "\nExam in " + exam.getSubject().getName()
							+ " writen by " + exam.getTeacher().getUsername() + "\nDuration: " + exam.getTimeString()
							+ " hours";
					examsdisc.add(discString);
				}
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

	public static String getTeacherCourses(String user, String paString) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();

		if (list.size() != 0) {
			Teacher teacher = (Teacher) query.getSingleResult();
			ObjectMapper mapper = new ObjectMapper();
			List<String> courses = new ArrayList<String>();
			for (Course c : teacher.getCourses()) {
				courses.add(c.getName());
			}
			String json = mapper.writeValueAsString(courses);
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
		List<String> items = teacher.getTodoList();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(items);
		dataBase.closeSess();
		System.out.println("JSON = " + json);
		return json;
	}

	public static String addToDo(String user, String paString, String item) {
		Teacher teacher = getTeacher(user, paString);
		Session session = dataBase.getSession();
		teacher.addTodoItem(item);
		session.update(teacher);
		session.getTransaction().commit();
		session.close();

		return "added";
	}

	public static String DellToDo(String user, String paString, String item) throws JsonProcessingException {
		Teacher teacher = getTeacher(user, paString);
		Session session = dataBase.getSession();
		int i = 0;
		for (String it : teacher.getTodoList()) {
			if (it.equals(item)) {
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

	public static String getTeachers() throws JsonProcessingException {
		dataBase.getInstance();
		ObjectMapper mapper = new ObjectMapper();
		List<String> teachersNames = new ArrayList<String>();
		teachersNames.add("ALL");
		for (Teacher t : dataBase.getAll(Teacher.class)) {

			String discString = "" + t.getUsername();
			teachersNames.add(discString);
		}
		System.out.println("Teachers: " + teachersNames);
		dataBase.closeSess();
		return mapper.writeValueAsString(teachersNames);
	}

	public static String questionExist(String qNumber, String sNumber) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery(
				"from Question where question_number = :question_number and subjectNumber = :subjectNumber");
		query.setParameter("question_number", qNumber);
		query.setParameter("subjectNumber", sNumber);
		List list = query.list();

		if (list.size() != 0) {
			dataBase.closeSess();
			return "exist";
		}
		dataBase.closeSess();
		return "good";
	}

	public static String getSubNumber(String subject) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Subject where subject_name = :subject_name");
		query.setParameter("subject_name", subject);

		List list = query.list();

		if (list.size() != 0) {
			Subject subj = (Subject) query.getSingleResult();
			dataBase.closeSess();
			return subj.getSnumber();
		}
		dataBase.closeSess();
		return "";

	}

	public static String addQuestion(String discription, String subjNumber, String an1, String an2, String an3,
			String an4, String rAnsewr) throws JsonProcessingException, SQLException {
		String qNString = "";
		do {
			qNString = Instance.getQN(2);

		} while ((commandRunner.run(Command.isQuestExist.ordinal() + "@" + qNString + "@" + subjNumber))
				.equals("exist"));
		Question question = new Question(discription, qNString, getSubject(subjNumber));
		dataBase.closeSess();
		question.setRightAnswer(rAnsewr);
		question.addAnswers(an1, an2, an3, an4);
		Session session = dataBase.getSession();
		session.save(question);
		session.getTransaction().commit();
		session.close();
		return "";
	}

	public static Subject getSubject(String sNumber) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Subject where subject_number = :subject_number");
		query.setParameter("subject_number", sNumber);
		List list = query.list();

		if (list.size() != 0) {
			Subject subject = (Subject) query.getSingleResult();
			return subject;
		}
		return null;
	}

	public static Subject getSubjectN(String name) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Subject where subject_name = :subject_name");
		query.setParameter("subject_name", name);
		List list = query.list();

		if (list.size() != 0) {
			Subject subject = (Subject) query.getSingleResult();
			return subject;
		}
		return null;
	}

	public static String getExamsByUsrName(String user) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username");
		query.setParameter("username", user);
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

}
