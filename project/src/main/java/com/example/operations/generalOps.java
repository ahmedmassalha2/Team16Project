package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.ServerClientEntities.Instance;
import com.example.entities.Course;
import com.example.entities.Exam;
import com.example.entities.Question;
import com.example.entities.Student;
import com.example.entities.Subject;
import com.example.entities.Teacher;
import com.example.entities.checkedExam;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class generalOps {
	public static String getQuestions() throws JsonProcessingException {
		List<Question> questions = dataBase.getAll(Question.class);
		List<String> quests = new ArrayList<String>();
		for (Question question : questions) {
			String queString = "Id: " + question.getId() + "\n" + question.getDiscription();
			quests.add(queString);
		}
		dataBase.closeSess();
		return getJsonString(quests);
	}

	public static String getSubjects() throws JsonProcessingException {
		List<Subject> subjects = dataBase.getAll(Subject.class);
		List<String> subjs = new ArrayList<String>();
		for (Subject subject : subjects) {
			subjs.add(subject.getName());
		}
		dataBase.closeSess();
		return getJsonString(subjs);
	}

	public static String getQuestion(String id) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Question question = session.get(Question.class, Integer.valueOf(id));
		/*
		 * Query query = session.createQuery("from Question where id = :id");
		 * query.setParameter("id", id); List list = query.list();
		 */

		if (question != null) {

			List<String> questionData = new ArrayList<String>();
			questionData.add(question.getDiscription());
			questionData.add(question.getSubject().getName());
			questionData.add(question.getNumber());
			questionData.add(question.getAnswers().get(0));
			questionData.add(question.getAnswers().get(1));
			questionData.add(question.getAnswers().get(2));
			questionData.add(question.getAnswers().get(3));
			questionData.add(question.getRightAnswer());
			System.out.println(getJsonString(questionData));
			return getJsonString(questionData);

		}
		return "";
	}

	public static String deleteQuestion(String subjectNumber, String questionNumber) {
		dataBase.getInstance();
		System.out.println(subjectNumber + "       " + questionNumber);
		Session session = dataBase.getSession();
		Query query = session.createQuery(
				"from Question where question_number = :question_number and subjectNumber = :subjectNumber");
		query.setParameter("question_number", questionNumber);
		query.setParameter("subjectNumber", subjectNumber);
		List list = query.list();

		if (list.size() != 0) {
			Question question = (Question) query.getSingleResult();
			if (question.getExams().size() != 0) {
				session.close();
				return "delted";
			}

			session.delete(session.get(Question.class, question.getId()));
			session.flush();
			session.getTransaction().commit();
			session.close();
			return "delted";
		}
		session.close();
		return "good";
	}

	public static String getSubjectByCourse(String courseName) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Course where name = :name");
		query.setParameter("name", courseName);

		List list = query.list();

		if (list.size() != 0) {

			Course course = (Course) query.getSingleResult();
			String ret = course.getSubject().getName() + "@" + course.getSubject().getSnumber();

			session.close();
			return ret;

		}
		session.close();
		return "";
	}

	public static String getJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	public static Subject getSubjectByName(String subName) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Subject where subject_name = :subject_name");
		query.setParameter("subject_name", subName);
		List list = query.list();
		if (list.size() != 0) {
			Subject sub = (Subject) query.getSingleResult();
			// session.close();
			return sub;
		}
		// session.close();
		return null;

	}

	public static Course getCourseByName(String courseName) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Course where name = :name");
		query.setParameter("name", courseName);
		List list = query.list();
		if (list.size() != 0) {
			Course course = (Course) query.getSingleResult();
			// session.close();
			return course;
		}
		// session.close();
		return null;

	}

	public static String getTechName(String user) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Teacher where username = :username");
		query.setParameter("username", user);
		List list = query.list();
		if (list.size() != 0) {
			String name = ((Teacher) query.getSingleResult()).getFirstName() + " "
					+ ((Teacher) query.getSingleResult()).getLastName();
			session.close();
			return name;
		}
		session.close();

		return null;
	}

	public static String getAllStudents() throws JsonProcessingException {
		List<Student> students = dataBase.getAll(Student.class);
		String ret = "";
		for (Student student : students) {
			String disc = "Student ID: " + student.getIdNum() + "\n" + "First Name: " + student.getFirstName() + "\n"
					+ "Last Name: " + student.getLastName();
			ret = ret + disc + "@";
		}
		dataBase.closeSess();
		return ret;
	}

	public static String getStudentByIDNUM(String idNum) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Student where idNum = :idNum");
		query.setParameter("idNum", idNum);
		List list = query.list();
		if (list.size() != 0) {
			String disc = "";
			Student student = ((Student) query.getSingleResult());

			List<checkedExam> exams = student.getGrades();
			// List<Course> courses = student.getCourses();
			List<String> coursesNAmes = new ArrayList<>();
			for (Course course : student.getCourses())
				coursesNAmes.add(course.getName());
			disc = student.getFirstName() + " " + student.getLastName() + "@" + student.getIdNum() + "@"
					+ generalOps.getJsonString(exams) + "@" + generalOps.getJsonString(coursesNAmes);
			session.close();
			return disc;
		}
		session.close();

		return null;
	}

	public static String getSTIDNum(String userName, String password) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Student where username = :username and password = :password");
		query.setParameter("username", userName);
		query.setParameter("password", password);
		List list = query.list();
		if (list.size() != 0) {
			String disc = "";
			String student = ((Student) query.getSingleResult()).getIdNum();
			session.close();
			return student;
		}
		session.close();
		return "";
	}

	public static String studentChecked(String string) throws JsonProcessingException {
		List<checkedExam> exams = dataBase.getAll(checkedExam.class);
		List<String> examsdisc = new ArrayList<>();
		for (checkedExam exam : exams) {
			if (exam.getStudent().getIdNum().equals(string)) {
				String discString = "Exam id: " + exam.getId() + "\nName: " + exam.getStudent().getFirstName() + " "
						+ exam.getStudent().getLastName() + "\nGrade: " + exam.getGrade() + "\nDuration: "
						+ exam.getTimeString() + " minutes";
				if (exam.isChecked())
					examsdisc.add(discString);
			}
		}
		dataBase.closeSess();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(examsdisc);
		System.out.println(json);
		return json;
	}

	public static String thisTeacherStudentChecked(String string) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		List<Exam> exams = dataBase.getAll(Exam.class);
		List<String> examsdisc = new ArrayList<>();
		List<checkedExam> checked = dataBase.getAll(checkedExam.class);
		System.out.println("teacher: " + string);
		for (Exam ex : exams) {
			Query query = session.createQuery("from Exam where id = :id");
			query.setParameter("id", ex.getId());
			Exam exam = (Exam) query.getSingleResult();
			if (exam.getTeacherGeneratedExam() != null) {
				Query query2 = session.createQuery("from Teacher where username = :username");
				query2.setParameter("username", string);
				Teacher teacher = (Teacher) query2.getSingleResult();
				if (exam.getTeacher().getId() == teacher.getId()
						|| Integer.parseInt(exam.getTeacherGeneratedExam()) == teacher.getId()) {
					System.out.println("TEACHER ID: " + teacher.getId());
					Query query3 = session.createQuery("from checkedExam where id = :id");
					query3.setParameter("id", exam.getId());

					for (checkedExam exa : checked) {
						if (exa.getTeacher().getId() == teacher.getId()
								|| exam.getTeacher().getId() == teacher.getId()) {
							if (exa.isChecked()) {
								String discString = "Exam id: " + exa.getId() + "\nName: "
										+ exa.getStudent().getFirstName() + " " + exa.getStudent().getLastName()
										+ "\nGrade: " + exa.getGrade() + "\nDuration: " + exa.getTimeString()
										+ " minutes\nCourse " + ex.getCourseName();
								examsdisc.add(discString);

							}
						}

					}
				}
			}
		}

		// session.update(object);
		session.close();
		dataBase.closeSess();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(examsdisc);
		System.out.println(json);
		return json;
	}
}
