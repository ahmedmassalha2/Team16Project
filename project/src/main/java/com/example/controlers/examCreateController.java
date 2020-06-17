
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class examCreateController implements Initializable {
	static String userString;
	static String paString;
	@FXML // fx:id="coursesFilt"
	private ComboBox<String> coursesFilt; // Value injected by FXMLLoader
	String subName = "";
	String subNum = "";
	@FXML // fx:id="durationTXT"
	private TextField durationTXT; // Value injected by FXMLLoader

	@FXML // fx:id="insertQ"
	private Button insertQ; // Value injected by FXMLLoader

	@FXML // fx:id="cancelBTN"
	private Button cancelBTN; // Value injected by FXMLLoader
	@FXML // fx:id="subjName"
	private Text subjName; // Value injected by FXMLLoader

	@FXML
	void cancel(ActionEvent event) {

	}

	@FXML
	void getCourseInfo(ActionEvent event) throws IOException {
		Instance.sendMessage(
				Command.getCourseSubject.ordinal() + "@" + coursesFilt.getSelectionModel().getSelectedItem());
		String[] argStrings = Instance.getClientConsole().getMessage().toString().split("@");
		subName = argStrings[0];
		subNum = argStrings[1];
		subjName.setText("Exam in: " + subName);

	}

	@FXML
	void insertQuestions(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/examQuestions.fxml"));
		Parent Main = loader.load();
		examsQuestionsController secController = loader.getController();
		examsQuestionsController.paString = examCreateController.paString;
		examsQuestionsController.userString = examCreateController.userString;
		secController.init(subName, subNum);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Questions list");
		Window.setScene(scene);
		Window.show();
	}

	public void init(String teacherName, String teacherPass) throws IOException {
		examCreateController.paString = teacherPass;
		examCreateController.userString = teacherName;
		filFilter(teacherName, teacherPass);
	}

	public void filFilter(String teacherName, String teacherPass) throws IOException {
		Instance.sendMessage(Command.teacherCourses.ordinal() + "@" + teacherName + "@" + teacherPass);
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		coursesFilt.getItems().addAll(l);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
