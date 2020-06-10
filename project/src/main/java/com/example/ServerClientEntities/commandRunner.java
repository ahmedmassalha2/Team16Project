package com.example.ServerClientEntities;

import java.sql.SQLException;

import com.example.operations.accOps;
import com.example.project.dataBase;

public class commandRunner {
	public static String run(String command) throws SQLException {
		dataBase.getInstance();
		String[] commandArr = command.split("@");
		Command command_ = Command.values()[Integer.valueOf(commandArr[0])];

		switch (command_) {
		case accExist:
			return accOps.logIn(commandArr[1], commandArr[2], commandArr[4]);

		default:
			break;
		}
		return command;

	}

}
