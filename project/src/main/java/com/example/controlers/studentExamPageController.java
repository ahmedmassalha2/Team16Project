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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class studentExamPageController implements Initializable {

	@FXML // fx:id="insertQ"
	private Button insertQ; // Value injected by FXMLLoader

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
	static String studName = "";
	static String studentIDString = "";
	static String userString = "";
	static String paString = "";
	static String duration = "";
	static String subName = "";
	static String cName = "";
	static String stInfo = "";
	static String teacherName = "";

	public void showData() {
		examDur.setText(duration);
		studentName.setText(studName);
		studentd.setText(studentIDString);
		studNotations.setText(stInfo);
		courseName.setText(cName);
		tacherName.setText(teacherName);
	}

	@FXML
	void addExam(ActionEvent event) {

	}

	@FXML
	void insertQuestions(ActionEvent event) {

	}

	public void initByExam(String data) throws IOException, SQLException {
		String[] datas = data.split("@");
		studentExamPageController.userString = datas[0];
		studentExamPageController.paString = datas[1];
		studentExamPageController.duration = datas[2];
		studentExamPageController.subName = datas[3];
		studentExamPageController.cName = datas[4];
		// studentExamQuestionsController.studentsInfo = new
		// ObjectMapper().readValue(datas[5], ArrayList.class);
		// studentExamQuestionsController.points = (new
		// ObjectMapper().readValue(datas[7], ArrayList.class));
		// studentExamQuestionsController.questIDs = new
		// ObjectMapper().readValue(datas[8], ArrayList.class);
		studentExamPageController.stInfo = datas[9];
		studentExamPageController.teacherName = getTeachName();
		showData();
	}

	public String getTeachName() throws IOException {
		Instance.sendMessage(Command.getTechName.ordinal() + "@" + userString);
		return Instance.getClientConsole().getMessage().toString();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}
