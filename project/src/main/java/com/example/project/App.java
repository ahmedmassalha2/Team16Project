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
		
		Question question = new Question("test question","000",s1);
		session.save(question);
		Answer a1 = new Answer("test", false, question);
		Answer a2 = new Answer("today", false, question);
		Answer a3 = new Answer("tomorrow", false, question);
		Answer a4 = new Answer("yet", true, question);
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.save(a4);
		
		Teacher teacher = new Teacher("teacher", "malki gr", "123");
		
		List<Question> list = new ArrayList<Question>();
		list.add(question);
		teacher.addCourses(c1);
		session.save(teacher);
		
		Teacher teacher2 = new Teacher("teacher", "wefwef gr", "123");
		Course c2 = new Course("dqw","00");
		session.save(c2);
		teacher2.addCourses(c2);
		session.save(teacher2);
		

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
		List<Course> c = getAll(Course.class);
		for(Course course : c) {
			System.out.println(course.getName());
			System.out.println(course.getTeachers().size());
		}
		List<Teacher> t = getAll(Teacher.class);
		for(Teacher teacher : t) {
			System.out.println(teacher.getUsername());
			System.out.println(teacher.getCourses().size());
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
