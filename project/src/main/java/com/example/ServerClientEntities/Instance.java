package com.example.ServerClientEntities;

public class Instance {
	public static SimpleChatClient clientConsole;

	public static SimpleChatClient getClientConsole() {
		return clientConsole;
	}

	public static void setClientConsole(SimpleChatClient clientConsole) {
		Instance.clientConsole = clientConsole;
	}

}
