package com.example.controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CheckExamShowQuestionsController {
	
    @FXML // fx:id="mainPane"
    private AnchorPane mainPane; // Value injected by FXMLLoader

    @FXML // fx:id="questionDisc"
    private TextArea questionDisc; // Value injected by FXMLLoader

    @FXML // fx:id="ans1"
    private TextArea ans1; // Value injected by FXMLLoader

    @FXML // fx:id="ans2"
    private TextArea ans2; // Value injected by FXMLLoader

    @FXML // fx:id="ans3"
    private TextArea ans3; // Value injected by FXMLLoader

    @FXML // fx:id="ans4"
    private TextArea ans4; // Value injected by FXMLLoader

    @FXML // fx:id="selc1"
    private RadioButton selc1; // Value injected by FXMLLoader

    @FXML // fx:id="tglG"
    private ToggleGroup tglG; // Value injected by FXMLLoader

    @FXML // fx:id="selc2"
    private RadioButton selc2; // Value injected by FXMLLoader

    @FXML // fx:id="selc3"
    private RadioButton selc3; // Value injected by FXMLLoader

    @FXML // fx:id="selc4"
    private RadioButton selc4; // Value injected by FXMLLoader

    @FXML // fx:id="nextBTN"
    private Button nextBTN; // Value injected by FXMLLoader

    @FXML // fx:id="backBTN"
    private Button backBTN; // Value injected by FXMLLoader

    @FXML // fx:id="questionsFilt"
    private ComboBox<?> questionsFilt; // Value injected by FXMLLoader

    @FXML // fx:id="inserBTN"
    private Button inserBTN; // Value injected by FXMLLoader

    @FXML // fx:id="studentInfo"
    private TextArea studentInfo; // Value injected by FXMLLoader

    @FXML // fx:id="teacherInfo"
    private TextArea teacherInfo; // Value injected by FXMLLoader

    @FXML // fx:id="poitns"
    private TextField poitns; // Value injected by FXMLLoader

    @FXML // fx:id="updatebtn"
    private Button updatebtn; // Value injected by FXMLLoader

    @FXML // fx:id="deletebtn"
    private Button deletebtn; // Value injected by FXMLLoader

    @FXML // fx:id="ErrorTXT2"
    private Text ErrorTXT2; // Value injected by FXMLLoader

    @FXML // fx:id="finishBTN"
    private Button finishBTN; // Value injected by FXMLLoader

    @FXML // fx:id="ErrorTXT"
    private Text ErrorTXT; // Value injected by FXMLLoader

    @FXML
    void backToMain(ActionEvent event) {

    }

    @FXML
    void changeCurrDowner(ActionEvent event) {

    }

    @FXML
    void changeCurrUpper(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void getQuestion(ActionEvent event) {

    }

    @FXML
    void insert(ActionEvent event) {

    }

    @FXML
    void update(ActionEvent event) {

    }

}
