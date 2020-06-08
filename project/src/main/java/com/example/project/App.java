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
		Course c1 = new Course("hedva","00");
		session.save(c1);
		
		Subject s1 = new Subject("math","00");
		session.save(s1);
		
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
		
		Question question2 = new Question("1 * 6 = ?","000",s1);
		session.save(question2);
		Answer a11 = new Answer("1 * 6 = 2", false, question2);
		Answer a12 = new Answer("1 * 6 = 3", false, question2);
		Answer a13 = new Answer("1 * 6 = 6", false, question2);
		Answer a14 = new Answer("1 * 6 = 5", true, question2);
		session.save(a11);
		session.save(a12);
		session.save(a13);
		session.save(a14);
		
		Teacher teacher = new Teacher("teacher", "malki gr", "123");
		
		List<Question> list = new ArrayList<Question>();
		list.add(question);
		list.add(question2);
		teacher.addCourses(c1);
		session.save(teacher);
		
		Exam exam = new Exam(teacher, s1, list,"1:00" , c1);
		session.save(exam);
		

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
		for(Exam exam : exams) {
			System.out.println("Exam writen by: " + exam.getTeacher().getUsername());
			System.out.println("time for exam: " + exam.getTimeString());
			System.out.println("\n\nQuestions");
			int i = 1;
			for(Question question : exam.getQuestions()) {
				System.out.print(i + ")"+question.getDiscription() + "\n");
				for(Answer answer : question.getAnswers()) {
					System.out.println("    "+answer.getAnString());
				}
				i++;
			}
			
		}

		System.out.format("\n\n");
		System.out.println("Done!");
	}

	public static void main(String[] args) {
		try {
			Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
			 SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			initializeData();
			printData();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			session.close();
		}
	}
}
