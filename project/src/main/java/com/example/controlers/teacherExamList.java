/**
 * Sample Skeleton for 'teacherExamsList.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class teacherExamList implements Initializable {
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML // fx:id="ExamsList"
	private ListView<String> ExamsList; // Value injected by FXMLLoader
	@FXML // fx:id="showExambtn"
	private Button showExambtn; // Value injected by FXMLLoader

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void init(String teacherName, String teacherPass) throws IOException {
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole()
				.sendToServer(Command.teacherExams.ordinal() + "@" + teacherName + "@" + teacherPass);
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");

		}
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		ExamsList.getItems().addAll(l);
	}

	@FXML
	void showExam(ActionEvent event) {
		System.out.println(ExamsList.getSelectionModel().getSelectedItem().split("\n")[0].split(" ")[2]);
	}
}
