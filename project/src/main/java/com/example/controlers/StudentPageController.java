package com.example.controlers;

/**
 * Sample Skeleton for 'StudentMainPage.fxml' Controller Class
 */

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StudentPageController {


    @FXML // fx:id="gradesBtn"
    private Button gradesBtn; // Value injected by FXMLLoader

    @FXML // fx:id="enterExamBtn"
    private Button enterExamBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="feedbackBtn"
    private Button feedbackBtn; // Value injected by FXMLLoader

    @FXML
    void enterExam(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
    	
    	Stage stage = (Stage) backBtn.getScene().getWindow();
        // do what you have to do
        stage.close();

    }

    @FXML
    void showFeedback(ActionEvent event) {

    }

    @FXML
    void showGrades(ActionEvent event) {

    }

    @FXML
    void studentName(ActionEvent event) {
    	
    	//Student student = new Student()

    }

}
