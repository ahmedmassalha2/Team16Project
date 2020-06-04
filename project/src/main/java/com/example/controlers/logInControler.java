/**
 * Sample Skeleton for 'logIN.fxml' Controller Class
 */

package com.example.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class logInControler {

	@FXML // fx:id="userName"
	private TextField userName; // Value injected by FXMLLoader

	@FXML // fx:id="Password"
	private TextField Password; // Value injected by FXMLLoader

	@FXML // fx:id="logInBtn"
	private Button logInBtn; // Value injected by FXMLLoader

	@FXML
	void logIn(ActionEvent event) {

	}

}
