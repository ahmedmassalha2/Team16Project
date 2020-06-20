package com.example.controlers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OpenCheckedExamController {

	@FXML // fx:id="toQuestions"
    private Button toQuestions; // Value injected by FXMLLoader

    @FXML // fx:id="studNotations"
    private TextArea studNotations; // Value injected by FXMLLoader

    @FXML // fx:id="submitBTN"
    private Button submitBTN; // Value injected by FXMLLoader

    @FXML // fx:id="studentName"
    private Text studentName; // Value injected by FXMLLoader

    @FXML // fx:id="examDur"
    private Text examDur; // Value injected by FXMLLoader

    @FXML // fx:id="tacherName"
    private Text tacherName; // Value injected by FXMLLoader

    @FXML // fx:id="systemGrade"
    private Text systemGrade; // Value injected by FXMLLoader

    @FXML // fx:id="teacherComments"
    private TextArea teacherComments; // Value injected by FXMLLoader

    @FXML // fx:id="lastGrade"
    private TextField lastGrade; // Value injected by FXMLLoader


    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void addExam(ActionEvent event) {
    	
    	

    }

    @FXML
    void showQuestions(ActionEvent event) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/CheckExamShowQuestions.fxml"));
		Parent Main = loader.load();
		CheckExamShowQuestionsController secController = loader.getController();

		//secController.initByExam(Instance.getClientConsole().getMessage().toString());
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Create exam main page");
		Window.setScene(scene);
		Window.show();

    }
    
    static String teacherName = "";
    static String studtName = "";
	static String time = "";
	static String grade = "";
	static String teacherCommentsExam = "";

    public void initByExam(String data) throws IOException, SQLException {
		String[] datas = data.split("@");
		OpenCheckedExamController.teacherName = datas[0];
		OpenCheckedExamController.studtName = datas[1];
		OpenCheckedExamController.time = datas[2];
		OpenCheckedExamController.grade = datas[4];
		OpenCheckedExamController.teacherCommentsExam = datas[6];
		showData();
	}
    public void showData() {
		examDur.setText(time);
		tacherName.setText(teacherName);
		studentName.setText(studtName);
		systemGrade.setText(grade);
		teacherComments.setText(teacherCommentsExam);

	}
    
}
