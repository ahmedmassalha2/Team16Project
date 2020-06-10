/**
 * Sample Skeleton for 'StudentMainPage.fxml' Controller Class
 */

package com.example.controlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class StudentPageController implements Initializable {

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

	@FXML
	void enterExam(ActionEvent event) {

	}

	@FXML
	void goBack(ActionEvent event) {

	}

	@FXML
	void showFeedback(ActionEvent event) {

	}

	@FXML
	void showGrades(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void init(String disc) {
		usernameTXT.setText(disc);
	}

}
