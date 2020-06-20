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
import com.example.entities.Student;
import com.example.entities.Subject;
import com.example.entities.Teacher;
import com.example.entities.checkedExam;
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

	public static String getSubjectName() throws JsonProcessingException {
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

		System.out.println("Subject: " + subject);
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where subjectName = :subjectName");
		query.setParameter("subjectName", subject);
		List<Exam> exams = query.getResultList();
		List<String> examsdisc = new ArrayList<String>();
		List list = query.list();
		if (list.size() != 0) {
			System.out.println("Entered exam subject");
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

	public static String addExam(String teacherName, String teacherPass, String examDuration, String subName,
			String courseName, List<String> infoPerQStudent, List<String> infoPerQTeacher,
			List<Double> gradePerQuestion, List<String> questionsIds, String infoExamStudent, String infoExamTeacher)
			throws JsonProcessingException, SQLException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		String examNum = Instance.getQN(2);
		while ((commandRunner.run(Command.isExamExist.ordinal() + "@" + examNum + "@" + subName + "@" + courseName))
				.equals("exist")) {
			examNum = Instance.getQN(2);
			System.out.println("Waiting for server");
		}

		List<Question> questionsList = new ArrayList<Question>();
		session = dataBase.getSession();
		for (String s : questionsIds) {
			System.out.println(s);

			Question q = session.get(Question.class, Integer.valueOf(s));
			questionsList.add(q);

		}
		Teacher teacher = teacherOps.getTeacher(teacherName, teacherPass);
		Subject subject = generalOps.getSubjectByName(subName);
		Course course = generalOps.getCourseByName(courseName);
		Exam exam = new Exam(teacher, subject, questionsList, examDuration, course);

		exam.setExamNumber(examNum);
		exam.setQuestions(questionsList);
		exam.setCourse(course);
		exam.setTeacher(teacher);
		exam.setSubject(subject);
		exam.setGradesPerQuestion(gradePerQuestion);

		exam.setStudentExamComments(infoExamStudent);
		exam.setStudentInfoPerQuestion(infoPerQStudent);
		exam.setTeacherExamComments(infoExamTeacher);
		exam.setTeacherInfoPerQuestion(infoPerQTeacher);
		session.save(exam);
		session.getTransaction().commit();
		session.close();
		dataBase.closeSess();
		return "";
	}

	public static String examExist(String examNum, String sName, String cName) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query2 = session.createQuery("from Subject where subject_name = :subject_name");
		query2.setParameter("subject_name", sName);
		Subject s = (Subject) query2.getSingleResult();
		int subId = s.getId();
		Query query3 = session.createQuery("from Course where name = :name");
		query3.setParameter("name", cName);
		Course c = (Course) query3.getSingleResult();
		int courseId = c.getId();
		Query query = session.createQuery("from Exam where exam_num = :exam_num and subject_name = :subject_name "
				+ "and course_id = :course_id");
		query.setParameter("exam_num", examNum);
		query.setParameter("subject_name", sName);
		query.setParameter("course_id", courseId);
		List list = query.list();

		if (list.size() != 0) {
			dataBase.closeSess();
			return "exist";
		}
		dataBase.closeSess();
		return "good";
	}

	public static String getWholeExam(String examNum) throws JsonProcessingException {

		System.out.println(examNum);
		dataBase.getInstance();
		Session session = dataBase.getSession();
		List<String> examsdisc = new ArrayList<String>();
		ObjectMapper mapper = new ObjectMapper();
		Query query = session.createQuery("from Exam where exam_num = :exam_num");
		query.setParameter("exam_num", examNum);
		Exam exam = (Exam) query.getSingleResult();
		String StudentInfoPerQuestion = mapper.writeValueAsString(exam.getStudentInfoPerQuestion());
		String TeachertInfoPerQuestion = mapper.writeValueAsString(exam.getTeacherInfoPerQuestion());
		String GradesPerQuestion = mapper.writeValueAsString(exam.getGradesPerQuestion());
		List<String> questionsId = new ArrayList<String>();
		for (Question q : exam.getQuestions()) {

			questionsId.add(String.valueOf(q.getId()));
		}
		String QuestionIds = mapper.writeValueAsString(questionsId);

		String examString = "" + exam.getTeacher().getUsername() + "@" + exam.getTeacher().getPassword() + "@"
				+ exam.getTimeString() + "@" + exam.getSubjectName() + "@" + exam.getCourseName() + "@"
				+ StudentInfoPerQuestion + "@" + TeachertInfoPerQuestion + "@" + GradesPerQuestion + "@" + QuestionIds
				+ "@" + exam.getStudentExamComments() + "@" + exam.getTeacherExamComments();

		System.out.println(examString);
		return examString;

	}

	public static String getExamCodetById(String id) {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where id = :id");
		System.out.println("Exam number: " + id);
		int i = Integer.parseInt(id);
		query.setParameter("id", i);
		Exam exam = (Exam) query.getSingleResult();
		Query query2 = session.createQuery("from Course where id = :id");
		String courseId = Integer.toString(exam.getCourse().getId());
		query2.setParameter("id", exam.getCourse().getId());
		Course course = (Course) query2.getSingleResult();
		session.close();
		return "" + course.getCnumber() + "" + exam.getExamNumber();
	}

	public static String getExamById(String id) throws JsonProcessingException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Exam q = session.get(Exam.class, Integer.valueOf(id));
		if (q != null) {
			String args = getWholeExam(q.getExamNumber());
			session.close();
			return args;
		}
		session.close();
		return "";
	}

	public static String isStudentExistById(String id, String usrName, String password) {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery(
				"from Student where idNum = :idNum and " + "username = :username and password = :password");
		query.setParameter("idNum", id);
		query.setParameter("username", usrName);
		query.setParameter("password", password);
		List list = query.list();
		if (list.size() != 0) {
			dataBase.closeSess();
			return "exist";
		}
		return "";
	}

	public static String getExamCourseByCode(String examCode) {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where exam_code = :exam_code");
		query.setParameter("exam_code", examCode);
		List list = query.list();
		if (list.size() != 0) {
			Exam exam = (Exam) query.getSingleResult();
			String course = exam.getCourseName();
			return course;
		}
		return "";
	}

	public static String isStudentExistInCourse(String id, String courseName) {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Student where idNum = :idNum");
		query.setParameter("idNum", id);
		Query query2 = session.createQuery("from Course where name = :name");
		query2.setParameter("name", courseName);
		List list = query.list();
		if (list.size() != 0) {
			Student student = (Student) query.getSingleResult();
			// List<Student> students = course.getStudents();
			Course course = (Course) query2.getSingleResult();
			if (course.getStudents().contains(student)) {
				session.close();
				dataBase.closeSess();
				return "exist";
			}

		}
		session.close();
		dataBase.closeSess();
		return "";
	}

	public static String getExamIdBycode(String code) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where exam_code = :exam_code");
		query.setParameter("exam_code", code);
		List list = query.list();
		if (list.size() != 0) {
			Exam exam = (Exam) query.getSingleResult();
			String id = Integer.toString(exam.getId());
			session.close();
			return id;
		}
		session.close();
		return "";
	}

	public static String setExamByExamNum(String examNum, String examCode, String teacherName) {
		dataBase.getInstance();

		Session session = dataBase.getSession();
		System.out.println("Exam num = " + examNum);
		Query query = session.createQuery("from Exam where exam_num = :exam_num");
		query.setParameter("exam_num", examNum);
		List list = query.list();

		if (list.size() != 0) {
			Query query2 = session.createQuery("from Teacher where username = :username");
			query2.setParameter("username", teacherName);
			Exam exam = (Exam) query.getSingleResult();
			Teacher teacher = (Teacher) query2.getSingleResult();
			exam.setExamCode(examCode);
			exam.setTeacherGeneratedExam(Integer.toString(teacher.getId()));
			session.update(exam);
			session.getTransaction().commit();
			session.close();
			System.out.println("Exam code = " + exam.getExamCode());
			System.out.println("Teacher: " + exam.getTeacherGeneratedExam());

		}

		return "";
	}

	public static String getIdByUsrName(String usrName) {

		dataBase.getInstance();

		Session session = dataBase.getSession();
		Query query = session.createQuery("from Student where username = :username");
		query.setParameter("username", usrName);
		List list = query.list();
		if (list.size() != 0) {
			Student student = (Student) query.getSingleResult();
			String toRetString = "" + student.getFirstName() + " " + student.getLastName();
			session.close();
			return toRetString;

		}
		session.close();
		return "";
	}

	public static String getTechIdByExCode(String string) {
		dataBase.getInstance();

		Session session = dataBase.getSession();
		Query query = session.createQuery("from Exam where exam_code = :exam_code");
		query.setParameter("exam_code", string);
		List list = query.list();
		if (list.size() != 0) {
			Exam exam = (Exam) query.getSingleResult();
			String toRetString = exam.getTeacherGeneratedExam();
			session.close();
			return toRetString;

		}
		session.close();
		return "";
	}

	public static String evaluateExam(checkedExam examToCheck, Exam exam) {
		Session session = dataBase.getSession();
		List<String> studentAnswers = examToCheck.getStudentAnswers();
		List<Question> questions = examToCheck.getQuestions();
		List<Double> grades = exam.getGradesPerQuestion();
		double res = 0;
		for (int i = 0; i < studentAnswers.size(); i++) {
			if (studentAnswers.get(i).equals(questions.get(i).getRightAnswer()))
				res += grades.get(i);
		}
		examToCheck.setGrade(res);
		session.save(examToCheck);
		session.getTransaction().commit();
		session.close();
		return "";
	}

	public static String examSubmmited(String studentId, List<String> answersList, String examCode, String teacherId) {

		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Student where idNum = :idNum");
		query.setParameter("idNum", studentId);
		Student student = (Student) query.getSingleResult();
		Query query2 = session.createQuery("from Exam where exam_code = :exam_code");
		query2.setParameter("exam_code", examCode);
		Exam exam = (Exam) query2.getSingleResult();
		Query query3 = session.createQuery("from Teacher where id = :id");
		query3.setParameter("id", Integer.valueOf(teacherId));
		Teacher teacher = (Teacher) query3.getSingleResult();
		checkedExam chExam = new checkedExam(teacher, exam.getSubject(), null, exam.getTimeString(), student, 0.0, "");
		chExam.setStudentAnswers(answersList);
		chExam.setStudent(student);
		chExam.setTeacher(teacher);
		chExam.setQuestions(new ArrayList<Question>());
		for (Question q : exam.getQuestions()) {
			chExam.getQuestions().add(q);
			q.getCheckedExams().add(chExam);
		}
		chExam.setTeacherInfoPerQuestion(new ArrayList<>());
		for (String s : exam.getStudentInfoPerQuestion())
			chExam.getTeacherInfoPerQuestion().add(s);

		chExam.setTeacherExamComments(exam.getStudentExamComments());
		ExamOps.evaluateExam(chExam, exam);
		return "";
	}
}
