package com.example.controlers;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class TeacherShowStudentsExam {
	
    @FXML // fx:id="backBTN"
    private Button backBTN; // Value injected by FXMLLoader

    @FXML // fx:id="openExamBtn"
    private Button openExamBtn; // Value injected by FXMLLoader

    @FXML // fx:id="ExamsList"
    private ListView<String> ExamsList; // Value injected by FXMLLoader

    @FXML
    void back(ActionEvent event) throws IOException {

    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/CheckExam.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		CheckExamController ses = loader.getController();
		ses.init(CheckExamController.username, CheckExamController.password, false);
		Window.setTitle("Check Exams list");
		Window.setScene(scene);
		Window.show();
    	

    }

    @FXML
    void openExam(ActionEvent event) {

    }
    public void init(List<String> examsDisc) {
		ExamsList.getItems().addAll(examsDisc);
	}

}
