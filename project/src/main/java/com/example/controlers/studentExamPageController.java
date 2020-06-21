/**
 * Sample Skeleton for 'studentExamPage.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.ServerClientEntities.commandRunner;
import com.example.entities.Course;
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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class studentExamPageController implements Initializable {

	@FXML // fx:id="toQuestions"
	private Button toQuestions; // Value injected by FXMLLoader

	@FXML // fx:id="studNotations"
	private TextArea studNotations; // Value injected by FXMLLoader

	@FXML // fx:id="submitBTN"
	private Button submitBTN; // Value injected by FXMLLoader

	@FXML // fx:id="courseName"
	private Text courseName; // Value injected by FXMLLoader

	@FXML // fx:id="studentName"
	private Text studentName; // Value injected by FXMLLoader

	@FXML // fx:id="studentd"
	private Text studentd; // Value injected by FXMLLoader

	@FXML // fx:id="examDur"
	private Text examDur; // Value injected by FXMLLoader

	@FXML // fx:id="tacherName"
	private Text tacherName; // Value injected by FXMLLoader

	@FXML // fx:id="backBtn"
	private Button backBtn; // Value injected by FXMLLoader
	static String studName = "";
	static String studentIDString = "";
	static String userString = "";
	static String paString = "";
	static String duration = "";
	static String subName = "";
	static String cName = "";
	static String stInfo = "";
	static String teacherName = "";

	static String setTeacher = "";

	public void showData() {
		examDur.setText(duration);
		studentName.setText(studName);
		studentd.setText(studentIDString);
		studNotations.setText(stInfo);
		courseName.setText(cName);
		tacherName.setText(teacherName);
	}

	@FXML
	void addExam(ActionEvent event) throws IOException {
		Instance.sendMessage(Command.studentSubmmit.ordinal() + "@" + studentExamQuestionsController.getData());
		resetAll();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Main page");
		Window.setScene(scene);
		Window.show();
	}

	@FXML
	void showQuestions(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/studentExamQuestions.fxml"));
		Parent Main = loader.load();
		studentExamQuestionsController secController = loader.getController();
		examsQuestionsController.paString = examCreateController.paString;
		secController.viewQuest();
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Questions list");
		Window.setScene(scene);
		Window.show();
	}

	public void initByExam(String data) throws IOException, SQLException {
		String[] datas = data.split("@");
		studentExamPageController.userString = datas[0];
		studentExamPageController.paString = datas[1];
		studentExamPageController.duration = datas[2];
		studentExamPageController.subName = datas[3];
		studentExamPageController.cName = datas[4];
		studentExamQuestionsController.studentsInfo = new ObjectMapper().readValue(datas[5], ArrayList.class);
		studentExamQuestionsController.points = (new ObjectMapper().readValue(datas[7], ArrayList.class));
		studentExamQuestionsController.questIDs = new ObjectMapper().readValue(datas[8], ArrayList.class);
		studentExamPageController.stInfo = datas[9];
		studentExamPageController.teacherName = setTeacher;
		showData();
		studentExamQuestionsController.loadDiscriptions();
	}

	public String getTeachName() throws IOException {
		Instance.sendMessage(Command.getTechName.ordinal() + "@" + userString);
		return Instance.getClientConsole().getMessage().toString();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public static void resetAll() {
		studName = "";
		studentIDString = "";
		userString = "";
		paString = "";
		duration = "";
		subName = "";
		cName = "";
		stInfo = "";
		teacherName = "";
		setTeacher = "";
		studentExamQuestionsController.resetAll();
	}

	@FXML
	void goBack(ActionEvent event) {

	}
}
