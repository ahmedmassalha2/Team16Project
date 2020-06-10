package com.example.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class teacherMainPageController implements Initializable {
	@FXML // fx:id="usernameTXT"
	private Text usernameTXT; // Value injected by FXMLLoader

	@FXML // fx:id="myExamsBTN"
	private Button myExamsBTN; // Value injected by FXMLLoader

	@FXML
	void showExamsList(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void init(String disc) {
		usernameTXT.setText(disc);
	}

}
