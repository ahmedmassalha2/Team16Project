
package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.entities.checkedExam;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class examCreateController implements Initializable {
	static String userString;
	static String paString;
	static String selection;
	@FXML // fx:id="coursesFilt"
	private ComboBox<String> coursesFilt; // Value injected by FXMLLoader
	static String subName = "";
	static String subNum = "";
	@FXML // fx:id="durationTXT"
	private TextField durationTXT; // Value injected by FXMLLoader
	@FXML // fx:id="errorTXT"
	private Text errorTXT; // Value injected by FXMLLoader
	@FXML // fx:id="insertQ"
	private Button insertQ; // Value injected by FXMLLoader
	@FXML // fx:id="techNotations"
	private TextArea techNotations; // Value injected by FXMLLoader

	@FXML // fx:id="studNotations"
	private TextArea studNotations; // Value injected by FXMLLoader
	@FXML // fx:id="cancelBTN"
	private Button cancelBTN; // Value injected by FXMLLoader
	@FXML // fx:id="subjName"
	private Text subjName; // Value injected by FXMLLoader

	@FXML // fx:id="finishBTN"
	private Button finishBTN; // Value injected by FXMLLoader
	@FXML // fx:id="submitBTN"
	private Button submitBTN; // Value injected by FXMLLoader
	static String Duration = "";
	static String stInfo = "";
	static String techInfo = "";

	@FXML
	void cancel(ActionEvent event) throws IOException {
		reset();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherExamsList.fxml"));
		Parent Main = loader.load();
		teacherExamList secController = loader.getController();
		secController.init(teacherExamList.useString, teacherExamList.passString);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Questions list");
		Window.setScene(scene);
		Window.show();
	}

	@FXML
	void getCourseInfo(ActionEvent event) throws IOException {
		selection = coursesFilt.getSelectionModel().getSelectedItem();
		getSubjectName();
	}

	public void getSubjectName() throws IOException {
		Instance.sendMessage(Command.getCourseSubject.ordinal() + "@" + selection);
		String[] argStrings = Instance.getClientConsole().getMessage().toString().split("@");
		subName = argStrings[0];
		subNum = argStrings[1];
		subjName.setText("Exam in: " + subName);
	}

	@FXML
	void insertQuestions(ActionEvent event) throws IOException {
		if (!check()) {
			return;
		}
		Duration = durationTXT.getText();
		techInfo = techNotations.getText();
		stInfo = studNotations.getText();
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
		if (!Duration.equals(""))
			durationTXT.setText(Duration);
		filFilter(teacherName, teacherPass);
	}

	public void filFilter(String teacherName, String teacherPass) throws IOException {
		Instance.sendMessage(Command.teacherCourses.ordinal() + "@" + teacherName + "@" + teacherPass);
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		coursesFilt.getItems().addAll(l);

		durationTXT.setText(Duration);
		techNotations.setText(techInfo);
		studNotations.setText(stInfo);

	}

	public boolean check() {
		if (!(coursesFilt.getSelectionModel().getSelectedIndex() >= 0)) {
			System.out.println("empty");
			return false;
		}

		return true;
	}

	@FXML
	void addExam(ActionEvent event) throws JsonProcessingException, IOException {
		if (examsQuestionsController.questDiscriptions.size() == 0) {
			errorTXT.setText("Exam is empty");
			return;
		}
		if (durationTXT.getText().isEmpty()) {
			errorTXT.setText("Fill exam duration");
			return;
		}
		if (techNotations.getText().isEmpty()) {
			errorTXT.setText("Enter information for teachers");
			return;
		}
		if (studNotations.getText().isEmpty()) {
			errorTXT.setText("Enter information for students");
			return;
		}
		Instance.sendMessage(Command.isExamExist.ordinal() + "@" + examsQuestionsController.getData());
		// reset vars
		cancel(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void setSelection(String select) {
		coursesFilt.getSelectionModel().select(select);
		subjName.setText("Exam in: " + subName);
	}

	public static void reset() {
		userString = "";
		paString = "";
		Duration = "";
		examsQuestionsController.cancelAll();
	}

	@FXML
	void setDuration(ActionEvent event) {
		examCreateController.Duration = durationTXT.getText();
	}
}
