package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Question;
import com.example.entities.Subject;
import com.example.entities.Teacher;
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

	public static String getJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}