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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class teacherExamList implements Initializable {
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML // fx:id="ExamsList"
	private ListView<String> ExamsList; // Value injected by FXMLLoader
	@FXML // fx:id="showExambtn"
	private Button showExambtn; // Value injected by FXMLLoader
	@FXML // fx:id="backBTN"
	private Button backBTN; // Value injected by FXMLLoader

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exams list");
		Window.setScene(scene);
		Window.show();
	}

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
