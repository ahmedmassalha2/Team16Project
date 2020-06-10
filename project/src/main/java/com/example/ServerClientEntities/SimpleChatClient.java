package com.example.ServerClientEntities;

import com.example.client.AbstractClient;
import java.util.logging.Logger;

public class SimpleChatClient extends AbstractClient {
	private static final Logger LOGGER = Logger.getLogger(SimpleChatClient.class.getName());
	private Object message;
	private ChatClientCLI chatClientCLI;

	public SimpleChatClient(String host, int port) {
		super(host, port);
		this.chatClientCLI = new ChatClientCLI(this);
	}

	@Override
	protected void connectionEstablished() {
		// TODO Auto-generated method stub
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		System.out.println(msg.toString());
		setMessage(msg);
		chatClientCLI.displayMessage(msg);
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		super.connectionClosed();
		chatClientCLI.closeConnection();
	}
}
