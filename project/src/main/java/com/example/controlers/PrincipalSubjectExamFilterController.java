package com.example.controlers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class PrincipalSubjectExamFilterController implements Initializable{
	
    @FXML // fx:id="showExambtn"
    private Button showExambtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBTN"
    private Button backBTN; // Value injected by FXMLLoader

    @FXML // fx:id="filterBtn"
    private ComboBox<String> filterBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ExamsList"
    private ListView<String> ExamsList; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void showExam(ActionEvent event) {

    }

    @FXML
    void showFilter(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
