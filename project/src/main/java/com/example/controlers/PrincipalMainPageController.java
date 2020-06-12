package com.example.controlers;

//import java.awt.Button;
//import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class PrincipalMainPageController implements Initializable{
	
	static String password = "";
	static String username = "";
	   @FXML // fx:id="examsBtn"
	    private Button examsBtn; // Value injected by FXMLLoader

	    @FXML // fx:id="questionsBtn"
	    private Button questionsBtn; // Value injected by FXMLLoader

	    @FXML // fx:id="usrNameBtn"
	    private Text usrNameBtn; // Value injected by FXMLLoader
	    @FXML // fx:id="logoutBtn"
	    private Button logoutBtn; // Value injected by FXMLLoader

	    @FXML
	    void logOut(ActionEvent event) {
	    	
	    	Platform.exit();
	        System.exit(0);

	    }

	    @FXML
	    void showExams(ActionEvent event) {

	    }

	    @FXML
	    void showQuestions(ActionEvent event) {

	    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		usrNameBtn.setText(username);

	}

	public void init(String disc, String password) {
		PrincipalMainPageController.username = disc;
		PrincipalMainPageController.password = password;
		usrNameBtn.setText(disc);
	}

}
