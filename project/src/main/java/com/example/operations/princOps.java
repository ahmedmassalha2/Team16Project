package com.example.operations;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Principal;
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
}
