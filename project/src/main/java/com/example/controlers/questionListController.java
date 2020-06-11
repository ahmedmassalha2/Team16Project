package com.example.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample Skeleton for 'questionList.fxml' Controller Class
 */

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class questionListController {
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML // fx:id="questionsList"
	private ListView<String> questionsList; // Value injected by FXMLLoader

	@FXML // fx:id="showBTN"
	private Button showBTN; // Value injected by FXMLLoader

	@FXML // fx:id="errorTXT"
	private Text errorTXT; // Value injected by FXMLLoader

	@FXML // fx:id="backbutton"
	private Button backbutton; // Value injected by FXMLLoader

	@FXML
	void back_(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exams list");
		Window.setScene(scene);
		Window.show();
	}

	@FXML
	void loadQ(ActionEvent event) {

	}

	public void init(String username, String password) throws IOException {
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole().sendToServer(Command.teacherQuestions.ordinal() + "@" + username + "@" + password);
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");
		}
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		questionsList.getItems().addAll(l);
	}

}
