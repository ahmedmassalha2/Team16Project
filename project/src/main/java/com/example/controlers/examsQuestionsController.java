/**
 * Sample Skeleton for 'examQuestions.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.entities.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class examsQuestionsController {

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
	@FXML // fx:id="inserBTN"
	private Button inserBTN; // Value injected by FXMLLoader
	@FXML // fx:id="questionsFilt"
	private ComboBox<String> questionsFilt; // Value injected by FXMLLoader
	static int Current = 0;
	static List<String> questDiscriptions = new ArrayList<String>();
	static List<String> questIDs = new ArrayList<String>();
	static List<ArrayList<String>> answers = new ArrayList<ArrayList<String>>();
	static String sName = "";
	static String sNumber = "";
	static String userString;
	static String paString;

	@FXML
	void changeCurrUpper(ActionEvent event) {
		if (Current + 1 >= questDiscriptions.size())
			return;
		Current += 1;
		System.out.println(Current);
	}

	@FXML
	void changeCurrDowner(ActionEvent event) {
		if (Current - 1 < 0)
			return;
		Current -= 1;
		System.out.println(Current);
	}

	public void init(String subName, String SubNumber) throws IOException {
		tglG.getToggles().setAll(selc1, selc2, selc3, selc4);
		if (!examsQuestionsController.sName.equals(subName)) {
			questDiscriptions.clear();
			questIDs.clear();
			answers.clear();
			sName = subName;
			sNumber = SubNumber;
		}
		loadQuestionList();
	}

	public void loadQuestionList() throws IOException {
		Instance.sendMessage(Command.teachQuesSubj.ordinal() + "@" + userString + "@" + paString + "@" + sName);
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> l = new ObjectMapper().readValue(json, ArrayList.class);
		questionsFilt.getItems().removeAll(questionsFilt.getItems());
		questionsFilt.getItems().addAll(l);
		updateQuestionList();
	}

	public void updateQuestionList() {

	}

	@FXML
	void getQuestion(ActionEvent event) throws IOException {
		reset();
		String id = questionsFilt.getSelectionModel().getSelectedItem().split("\n")[0].split(" ")[1];
		Instance.sendMessage(Command.getQ.ordinal() + "@" + id);
		List<String> l = new ObjectMapper().readValue(Instance.getClientConsole().getMessage().toString(),
				ArrayList.class);
		questionDisc.setText(l.get(0));
		ans1.setText(l.get(3));
		ans2.setText(l.get(4));
		ans3.setText(l.get(5));
		ans4.setText(l.get(6));
		setRightAns(l.get(7));

	}

	public void setRightAns(String answer) {
		if (ans1.getText().equals(answer))
			selc1.setSelected(true);
		if (ans2.getText().equals(answer))
			selc2.setSelected(true);
		if (ans3.getText().equals(answer))
			selc3.setSelected(true);
		if (ans4.getText().equals(answer))
			selc4.setSelected(true);
		return;
	}

	public void reset() {
		ans1.setText("");
		ans2.setText("");
		ans3.setText("");
		ans4.setText("");
		questionDisc.setText("");
		selc1.setSelected(false);
		selc2.setSelected(false);
		selc3.setSelected(false);
		selc4.setSelected(false);
	}

	@FXML
	void insert(ActionEvent event) {

	}
}
