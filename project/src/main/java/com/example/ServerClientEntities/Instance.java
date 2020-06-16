package com.example.ServerClientEntities;


public class Instance {
	public static SimpleChatClient clientConsole;

	public static SimpleChatClient getClientConsole() {
		return clientConsole;
	}

	public static void setClientConsole(SimpleChatClient clientConsole) {
		Instance.clientConsole = clientConsole;
	}

	public static String valQuestion(String disc, String qNumber, String sNumber) {
		if (disc.isBlank()) {
			return ("Fill question discription.");

		}
		if (!Instance.containCH(qNumber)) {
			return ("question number must contain only digits");
		}
		if (qNumber.length() != 3) {
			return ("question number must be 3 digits.");
		}
		if(sNumber.isBlank()) {
			return ("Select subject");
		}
		return "all good";
	}

	public static boolean containCH(String s) {
		for (char c : s.toCharArray()) {
			if (!(c >= '0' && c <= '9'))
				return false;
		}
		return true;
	}

}
