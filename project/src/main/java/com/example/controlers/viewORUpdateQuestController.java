/**
 * Sample Skeleton for 'viewORupdateQuestion.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.entities.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class viewORUpdateQuestController {
	static String password = "";
	static String username = "";
	Question quest;
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

	@FXML // fx:id="filterCombo"
	private ComboBox<String> filterCombo; // Value injected by FXMLLoader

	@FXML // fx:id="questionN"
	private TextField questionN; // Value injected by FXMLLoader

	@FXML // fx:id="backBTN"
	private Button backBTN; // Value injected by FXMLLoader

	@FXML // fx:id="submitBTN"
	private Button submitBTN; // Value injected by FXMLLoader

	@FXML // fx:id="ErrorTXT"
	private Text ErrorTXT; // Value injected by FXMLLoader

	// @FXML // fx:id="deletBTN"
	// private Button deletBTN; // Value injected by FXMLLoader
	@FXML // fx:id="subjName"
	private TextField subjName; // Value injected by FXMLLoader

	@FXML
	void addQuestion(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/questionList.fxml"));
		Scene scene = new Scene(loader.load());
		questionListController secController = loader.getController();
		secController.loadData();
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Main page");
		Window.setScene(scene);
		Window.show();
	}

	@FXML
	void getSNumber(ActionEvent event) {

	}

	public void init(String data, String disc, String password) throws IOException {
		viewORUpdateQuestController.username = disc;
		viewORUpdateQuestController.password = password;
		List<String> l = new ObjectMapper().readValue(data, ArrayList.class);
		questionDisc.setText(l.get(0));
		// to add in filter
		// loadSubjects(disc, password);
		subjName.setText(l.get(1));
		questionN.setText(l.get(2));
		ans1.setText(l.get(3));
		ans2.setText(l.get(4));
		ans3.setText(l.get(5));
		ans4.setText(l.get(6));
		setRightAns(l.get(7));
		quest = new Question();
		quest.setDiscription(questionDisc.getText());
		quest.setNumber(questionN.getText());
		Instance.sendMessage(Command.getSubjNumber.ordinal() + "@" + subjName.getText());
		quest.setSubjectNumber(Instance.getClientConsole().getMessage().toString());
	}

	public void setRightAns(String answer) {
		ObservableList<Toggle> ans = tglG.getToggles();
		if (ans1.getText().equals(answer))
			ans.get(0).setSelected(true);
		if (ans2.getText().equals(answer))
			ans.get(1).setSelected(true);
		if (ans3.getText().equals(answer))
			ans.get(2).setSelected(true);
		if (ans4.getText().equals(answer))
			ans.get(3).setSelected(true);
		return;
	}

	public void loadSubjects(String username, String password) throws IOException {
		Instance.sendMessage(Command.teacherSubjects.ordinal() + "@" + username + "@" + password);
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> ll = new ObjectMapper().readValue(json, ArrayList.class);
		ll.remove("All");
		filterCombo.getItems().removeAll(filterCombo.getItems());
		filterCombo.getItems().addAll(ll);
		filterCombo.getSelectionModel().select(0);
	}

	/*
	 * @FXML void deleteQuestion(ActionEvent event) {
	 * Instance.sendMessage(Command.teacherSubjects.ordinal() + "@" + username + "@"
	 * + password); }
	 */
}
