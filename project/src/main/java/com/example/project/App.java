package com.example.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.example.entities.Answer;
import com.example.entities.Course;
import com.example.entities.Exam;
import com.example.entities.Principal;
import com.example.entities.Question;
import com.example.entities.Student;
import com.example.entities.Subject;
import com.example.entities.Teacher;
import com.example.entities.checkedExam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App {
	private static Session session;

	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
// Add ALL of your entities here. You can also try adding a whole package.
		configuration.addAnnotatedClass(Answer.class);
		configuration.addAnnotatedClass(checkedExam.class);
		configuration.addAnnotatedClass(Course.class);
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(Principal.class);
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Teacher.class);
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void initializeData() throws Exception {
		Course c1 = new Course("hedva", "00");
		session.save(c1);

		Subject s1 = new Subject("math","00");
		session.save(s1);
		
		Course c2 = new Course("Introduction to CS", "01");
		session.save(c2);
		Subject s2 = new Subject("CS","01");
		session.save(s2);
		
		Course c3 = new Course("Data Structures", "02");
		session.save(c3);
		//Subject s3 = new Subject("CS_Semester B","02");
		session.save(s2);
		
		Question question = new Question("1 + 1 = ?","000",s1);

		session.save(question);
		Answer a1 = new Answer("1 + 1 = 2", false, question);
		Answer a2 = new Answer("1 + 1 = 3", false, question);
		Answer a3 = new Answer("1 + 1 = 4", false, question);
		Answer a4 = new Answer("1 + 1 = 5", true, question);
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.save(a4);

		
		Question question2 = new Question("1 * 6 = ?","001",s1);

		session.save(question2);
		Answer a11 = new Answer("1 * 6 = 2", false, question2);
		Answer a12 = new Answer("1 * 6 = 3", false, question2);
		Answer a13 = new Answer("1 * 6 = 6", false, question2);
		Answer a14 = new Answer("1 * 6 = 5", true, question2);
		session.save(a11);
		session.save(a12);
		session.save(a13);
		session.save(a14);

		
		Question question3 = new Question("if(1) : ", "002", s2);
		session.save(question3);
		Answer a = new Answer("Will enter the condition at 50% of times", false, question3);
		Answer b = new Answer("Will not enter the condition at all", false, question3);
		Answer c = new Answer("Will enter the condition always", true, question3);
		Answer d = new Answer("Will enter the condition at 90% of times", false, question3);
		session.save(a);
		session.save(b);
		session.save(c);
		session.save(d);
		
		Question question4 = new Question("while(!true) : ", "003", s2);
		session.save(question4);
		Answer a111 = new Answer("Will loop forever", true, question4);
		Answer b111 = new Answer("Will not loop at all", false, question4);
		Answer c111 = new Answer("Will loop sometimes", false, question4);
		Answer d111 = new Answer("Will loop at 90% of times", false, question4);
		session.save(a111);
		session.save(b111);
		session.save(c111);
		session.save(d111);
		
		Question question5 = new Question("T(n) = 2T(n/2) + o(n) = ?", "004", s2);
		session.save(question5);
		Answer answer1 = new Answer("O(n)", false, question5);
		Answer answer2 = new Answer("O(1)", false, question5);
		Answer answer3 = new Answer("O(nlogn)", true, question5);
		Answer answer4 = new Answer("O(logn)", false, question5);
		session.save(answer1);
		session.save(answer2);
		session.save(answer3);
		session.save(answer4);
		
		Question question6 = new Question("BST search time = ?", "005", s2);
		session.save(question6);
		Answer answer11 = new Answer("O(logn)", true, question6);
		Answer answer12 = new Answer("O(2^n)", false, question6);
		Answer answer13 = new Answer("O(n^2)", false, question6);
		Answer answer14 = new Answer("O(n)", false, question6);
		session.save(answer11);
		session.save(answer12);
		session.save(answer13);
		session.save(answer14);
		
		Teacher teacher = new Teacher("teacher", "malki gr", "123");
		Teacher teacher2 = new Teacher("teacher", "sholy weinter", "123");
		Teacher teacher3 = new Teacher("teacher", "oren weiman", "123");
		
		Student haitham = new Student("316379510", "student", "haitham17ad", "123456");
		Student ahmad = new Student("207876541", "student", "ahmad", "1234567");
		Student azmi = new Student("316768901", "student", "azmiabu", "12345678");
		
		List<Course> list_courses1 = new ArrayList<Course>();
		list_courses1.add(c1);
		list_courses1.add(c3);
		haitham.setCourses(list_courses1);
		session.save(haitham);
		
		List<Course> list_courses2 = new ArrayList<Course>();
		list_courses2.add(c2);
		list_courses2.add(c3);
		ahmad.setCourses(list_courses2);
		session.save(ahmad);
		
		List<Course> list_courses3 = new ArrayList<Course>();
		list_courses3.add(c1);
		list_courses3.add(c2);
		azmi.setCourses(list_courses3);
		session.save(azmi);
		
		

		List<Question> list = new ArrayList<Question>();
		list.add(question);
		list.add(question2);
		teacher.addCourses(c1);
		session.save(teacher);

		
		List<Question> list2 = new ArrayList<Question>();
		list2.add(question3);
		list2.add(question4);
		teacher.addCourses(c2);
		session.save(teacher2);
		
		List<Question> list3 = new ArrayList<Question>();
		list3.add(question5);
		list3.add(question6);
		teacher.addCourses(c3);
		session.save(teacher3);
		
		Exam exam = new Exam(teacher, s1, list,"1:00" , c1);
		session.save(exam);
		
		Exam exam2 = new Exam(teacher2, s2, list2, "1:00" , c2);
		session.save(exam2);
		
		Exam exam3 = new Exam(teacher3, s2, list3, "1:00" , c3);
		session.save(exam3);


		session.getTransaction().commit();

	}

	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	private static void printData() throws Exception {
		List<Exam> exams = getAll(Exam.class);

		List<Student> students = getAll(Student.class);
		//List<Course> courses = getAll(Course.class);
		for(Student student : students) {
			System.out.println("Student username: " + student.getUsername());
			System.out.println("Courses taken: ");
			//int i = 0;
			for(Course course : student.getCourses()) {
				System.out.println("" + course.getName());
			}
			System.out.println("");
		}
		System.out.println("");
		for(Exam exam : exams) {
			

			System.out.println("Exam writen by: " + exam.getTeacher().getUsername());
			System.out.println("time for exam: " + exam.getTimeString());
			System.out.println("\n\nQuestions");
			int i = 1;

			for(Question question : exam.getQuestions()) {
				//System.out.println("")
				System.out.print(i + ")"+question.getDiscription() + "\n");
				for(Answer answer : question.getAnswers()) {
					System.out.println("    "+answer.getAnString());
				}
				i++;
			}
			System.out.format("\n\n");
			System.out.println("Done!");
			System.out.format("\n\n");
			

		}
		
	}

	public static void main(String[] args) throws Exception {
		/*dataBase.getInstance();
		session = dataBase.getSession();
		initializeData();
		printData();
		dataBase.closeSess();*/
		/*Course c1 = new Course("122","1212");
		Subject s1 = new Subject("math", "00");
		Question question = new Question("1 + 1 = ?", "000", s1);
		Answer a1 = new Answer("1 + 1 = 2", false, question);
		Answer a2 = new Answer("1 + 1 = 3", false, question);
		Answer a3 = new Answer("1 + 1 = 4", false, question);
		Answer a4 = new Answer("1 + 1 = 5", true, question);

        ObjectMapper mapper = new ObjectMapper();
        List<Answer> l=question.getAnswers(); 
        try {
            String json = mapper.writeValueAsString(question);
            System.out.println("JSON = " + json);
            Question question1 = new ObjectMapper().readValue(json, Question.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
		dataBase.getInstance();
		session = dataBase.getSession();
		/*Query query = session
				.createQuery("from " + "Teacher where username = :username" + " where password = :password");
		query.setParameter("username", "malki gr");
		query.setParameter("password", "123");
		List list = query.list();
		System.out.println(list.size());*/
		Principal principal = new Principal("Principal", "Ahmad Massalha", "1234");
		session.save(principal);
		dataBase.closeSess();
	}
}
