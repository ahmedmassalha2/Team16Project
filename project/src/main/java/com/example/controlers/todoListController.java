/**
 * Sample Skeleton for 'todoList.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.entities.todoItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class todoListController {
	String userString;
	String paString;
	String role;
	@FXML // fx:id="addBTN"
	private Button addBTN; // Value injected by FXMLLoader

	@FXML // fx:id="textItem"
	private TextField textItem; // Value injected by FXMLLoader
	@FXML // fx:id="doneBTN"
	private Button doneBTN; // Value injected by FXMLLoader
	@FXML // fx:id="backBTN"
	private Button backBTN; // Value injected by FXMLLoader
	@FXML // fx:id="myTodoItems"
	private ListView<String> myTodoItems; // Value injected by FXMLLoader

	@FXML
	void addToDoItem(ActionEvent event) throws IOException {
		if (textItem.getText().isBlank())
			return;
		addItemToList(userString, paString);
	}

	public void init(String username, String password) throws IOException {
		userString = username;
		paString = password;
		loadList(username, password);

		Instance.getClientConsole().setMessage(null);
		while (Instance.getClientConsole().getMessage() != null) {
			System.out.println("waiting for server");
		}

	}

	public void loadList(String username, String password) throws IOException {
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole().sendToServer(Command.teacherToDo.ordinal() + "@" + username + "@" + password);
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");
		}
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		myTodoItems.getItems().removeAll(myTodoItems.getItems());
		myTodoItems.getItems().addAll(l);
	}

	public void addItemToList(String username, String password) throws IOException {
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole().sendToServer(
				Command.teachAddToDo.ordinal() + "@" + username + "@" + password + "@" + textItem.getText());
		while ((Instance.getClientConsole().getMessage()) == null) {
			System.out.println("waiting for server");
		}

		loadList(username, password);
	}

	@FXML
	void doneToDoItem(ActionEvent event) throws IOException {
		String selected=myTodoItems.getSelectionModel().getSelectedItem();
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole().sendToServer(
				Command.teachDellToDo.ordinal() + "@" + userString + "@" + paString + "@" + selected);
		while ((Instance.getClientConsole().getMessage()) == null) {
			System.out.println("waiting for server");
		}
		loadList(userString, paString);
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exams list");
		Window.setScene(scene);
		Window.show();
	}
}
