package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;

/**
 * Sample Skeleton for 'PrincipalTeacherExamFilter.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class PrincipalTeacherExamFilterController implements Initializable {

	@FXML // fx:id="showExambtn"
	private Button showExambtn; // Value injected by FXMLLoader

	@FXML // fx:id="backBTN"
	private Button backBTN; // Value injected by FXMLLoader

	@FXML // fx:id="filterBtn"
	private ComboBox<String> filterBtn; // Value injected by FXMLLoader

	@FXML // fx:id="ExamsList"
	private ListView<String> ExamsList; // Value injected by FXMLLoader

	@FXML
	void back(ActionEvent event) {

	}

	@FXML
	void showExam(ActionEvent event) {

	}

	@FXML
	void showFilter(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Instance.getClientConsole().setMessage(null);
		try {
			Instance.getClientConsole().sendToServer(""+Command.getTeachers.ordinal());
			while (Instance.getClientConsole().getMessage() == null) {
				System.out.println("waiting for server");
			}
			String json = Instance.getClientConsole().getMessage().toString();
			System.out.println("json: "+json);
			List<String> ll = new ObjectMapper().readValue(json, ArrayList.class);
			System.out.println("ll: "+ll);
			filterBtn.getItems().removeAll(filterBtn.getItems());
			filterBtn.getItems().addAll(ll);
			filterBtn.getSelectionModel().select(0);
			loadAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void loadAll() throws IOException {
		Instance.getClientConsole().setMessage(null);
		Instance.getClientConsole().sendToServer("" + Command.getAllExams.ordinal());
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");

		}

		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		ExamsList.getItems().removeAll(ExamsList.getItems());
		ExamsList.getItems().addAll(l);
	}

}
