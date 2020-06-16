package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Principal;
import com.example.entities.Question;
import com.example.entities.Subject;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class princOps {
	public static Principal getPrincipal(String user, String paString) {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		Query query = session.createQuery("from Principal where username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();

		if (list.size() != 0) {
			Principal principal = (Principal) query.getSingleResult();
			return principal;
		}
		return null;
	}

	public static String getToDo(String user, String paString) throws JsonProcessingException {
		Principal principal = getPrincipal(user, paString);
		List<String> items = principal.getTodoList();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(items);
		dataBase.closeSess();
		System.out.println("JSON = " + json);
		return json;
	}

	public static String addToDo(String user, String paString, String item) {
		Principal principal = getPrincipal(user, paString);
		Session session = dataBase.getSession();
		principal.addTodoItem(item);
		session.update(principal);
		session.getTransaction().commit();
		session.close();

		return "added";
	}

	public static String DellToDo(String user, String paString, String item) throws JsonProcessingException {
		Principal principal = getPrincipal(user, paString);
		Session session = dataBase.getSession();
		int i = 0;
		for (String it : principal.getTodoList()) {
			if (it.equals(item)) {
				principal.getTodoList().remove(i);
				break;
			}
			i++;
		}
		session.update(principal);
		session.getTransaction().commit();
		session.close();

		return "removed";
	}
	public static String getQuestSubjs(String subN) throws JsonProcessingException {
		Subject subject = teacherOps.getSubject(subN);
		List<String> quests = new ArrayList<String>();
		for(Question question : subject.getQuestions()) {
			String queString = "Id: " + question.getId() + "\n" + question.getDiscription();
			quests.add(queString);
		}
		dataBase.closeSess();
		return generalOps.getJsonString(quests);
	}
	public static String getQuestions() {
		try {
			return generalOps.getQuestions();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String getSubjects() {
		try {
			return generalOps.getSubjects();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
