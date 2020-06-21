/**
 * Sample Skeleton for 'StudentMainPage.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.example.project.App;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentPageController implements Initializable {
	int second = 0;
	Timer myTimer = new Timer();

	TimerTask task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			second++;
			mylabel.setText(Integer.toString(second));
			if (mylabel.getText().equals("5"))
				mylabel.setText("0");

		}
	};

	static String password = "";
	static String username = "";
	@FXML // fx:id="gradesBtn"
	private Button gradesBtn; // Value injected by FXMLLoader

	@FXML // fx:id="enterExamBtn"
	private Button enterExamBtn; // Value injected by FXMLLoader

	@FXML // fx:id="backBtn"
	private Button backBtn; // Value injected by FXMLLoader

	@FXML // fx:id="feedbackBtn"
	private Button feedbackBtn; // Value injected by FXMLLoader

	@FXML // fx:id="usernameTXT"
	private Text usernameTXT; // Value injected by FXMLLoader

	@FXML // fx:id="quotesBtn"
	private Button quotesBtn; // Value injected by FXMLLoader
	@FXML // fx:id="todoBTN"
	private Button todoBTN; // Value injected by FXMLLoader
	@FXML // fx:id="mylabel"
	private TextField mylabel; // Value injected by FXMLLoader

	@FXML
	void enterExam(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentStartExamPage.fxml"));
		Parent Main = loader.load();
		StudentStartExamPageController secController = loader.getController();

		secController.init(username, password);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exam Log In");
		Window.setScene(scene);
		Window.show();

	}

	@FXML
	void goBack(ActionEvent event) {

		Platform.exit();
		System.exit(0);
	}

	@FXML
	void showFeedback(ActionEvent event) {

	}

	@FXML
	void showGrades(ActionEvent event) {

	}

	@FXML
	void showQuotes(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentMotivationPage.fxml"));
		Parent Main = loader.load();
		// teacherExamList secController = loader.getController();
		// secController.init(username, password);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Daily Motivation");
		Window.setScene(scene);
		Window.show();
	}

	public void start() {
		myTimer.scheduleAtFixedRate(task, 1000, 1000);

	}

	@FXML
	void todoList(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/todoList.fxml"));
		Parent Main = loader.load();
		todoListController secController = loader.getController();

		secController.init(username, password, "Student");
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("ToDo list");
		Window.setScene(scene);
		Window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		usernameTXT.setText(username);
		//start();
	}

	public void init(String disc, String Password) {
		StudentPageController.username = disc;
		StudentPageController.password = Password;
		usernameTXT.setText(disc);
	}

}
