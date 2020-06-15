package com.example.operations;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Exam;
import com.example.entities.Teacher;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExamOps {
	
	
	public static String getExamsList() throws JsonProcessingException{
		
		dataBase.getInstance();
		
		ObjectMapper mapper = new ObjectMapper();

		//String json = mapper.writeValueAsString(examsdisc);
		//System.out.println("JSON = " + json);
		
		//return json;
		
		//Teacher teacher = (Teacher) query.getSingleResult();
	//	List<Exam> l = teacher.getExams();
		List<String> examsdisc = new ArrayList<String>();
		for (Exam exam : dataBase.getAll(Exam.class)) {

			String discString = "Exam id: " + exam.getId() + "\nExam in " + exam.getSubject().getName()
					+ " writen by " + exam.getTeacher().getUsername() + "\nDuration: " + exam.getTimeString()
					+ " hours";
			examsdisc.add(discString);
		}
		dataBase.closeSess();
		return mapper.writeValueAsString(examsdisc);
	}
	

}
