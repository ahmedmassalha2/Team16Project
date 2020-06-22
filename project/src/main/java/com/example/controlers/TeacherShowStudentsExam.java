package com.example.controlers;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class TeacherShowStudentsExam {
	
    @FXML // fx:id="backBTN"
    private Button backBTN; // Value injected by FXMLLoader

    @FXML // fx:id="openExamBtn"
    private Button openExamBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ExamsList"
    private ListView<String> ExamsList; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void openExam(ActionEvent event) {

    }
    public void init(List<String> examsDisc) {
		ExamsList.getItems().addAll(examsDisc);
	}

}
