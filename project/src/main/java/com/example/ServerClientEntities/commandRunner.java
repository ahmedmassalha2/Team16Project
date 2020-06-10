package com.example.ServerClientEntities;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Principal;
import com.example.entities.Student;
import com.example.entities.Teacher;
import com.example.project.dataBase;

public class commandRunner {
	public static String run(String command) throws SQLException {
		dataBase.getInstance();
		Session session = dataBase.getSession();
		String[] commandArr = command.split("@");
		if (commandArr[0].equals("userExist")) {
			String user = commandArr[1];
			String paString = commandArr[2];

			String tyString = commandArr[4];
			System.out.println("from " + tyString + " where username = :username and password = :password");
			Query query = session
					.createQuery("from " + tyString + " where username = :username and password = :password");
			query.setParameter("username", user);
			query.setParameter("password", paString);
			List list = query.list();
			if (list.size() != 0) {
				if (tyString.equals("Student")) {
					Student student = (Student) query.getSingleResult();
					if (student != null)
						return "Student@" + student.getUsername();
				}
				if (tyString.equals("Teacher")) {
					Teacher teacher = (Teacher) query.getSingleResult();
					if (teacher != null)
						return "Teacher@" + teacher.getUsername();
				}
				if (tyString.equals("Principal")) {
					Principal principal = (Principal) query.getSingleResult();
					if (principal != null)
						return "Principal@" + principal.getUsername();
				}
				return "";
			}

			return "";
		}
		return command;

	}

}
