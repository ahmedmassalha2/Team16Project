package com.example.ServerClientEntities;

import java.sql.SQLException;

import javax.persistence.criteria.CriteriaBuilder.Case;

import com.example.operations.ExamOps;
import com.example.operations.accOps;
import com.example.operations.teacherOps;
import com.example.project.dataBase;
import com.fasterxml.jackson.core.JsonProcessingException;

public class commandRunner {
	public static String run(String command) throws SQLException, JsonProcessingException {
		dataBase.getInstance();
		System.out.println(command);
		String[] commandArr = command.split("@");
		Command command_ = Command.values()[Integer.valueOf(commandArr[0])];

		switch (command_) {
		case accExist:
			return accOps.logIn(commandArr[1], commandArr[2], commandArr[4]);

		case teacherExams:
			return teacherOps.getExams(commandArr[1], commandArr[2]);

		case teacherQuestions:
			return teacherOps.getQuestions(commandArr[1], commandArr[2]);
		case teachQuesSubj:
			return teacherOps.getQuestionsSubject(commandArr[1], commandArr[2], commandArr[3]);
		case teacherSubjects:
			return teacherOps.getTeacherSubjects(commandArr[1], commandArr[2]);
		case teacherToDo:
			return teacherOps.getToDo(commandArr[1], commandArr[2]);
		case teachAddToDo:
			return teacherOps.addToDo(commandArr[1], commandArr[2], commandArr[3]);
		case teachDellToDo:
			return teacherOps.DellToDo(commandArr[1], commandArr[2], commandArr[3]);
		case getAllExams:
			return ExamOps.getExamsList();
		case getTeachers:
			return teacherOps.getTeachers();
		}

		return command;

	}

}
