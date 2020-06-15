/**
 * Sample Skeleton for 'addQuestion.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addQuestionController {
	String userString;
	String paString;
	String Snumber = "";
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

	@FXML
	void addQuestion(ActionEvent event) {

	}

	@FXML
	void back(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Main page");
		Window.setScene(scene);
		Window.show();
	}

	@FXML
	void getSNumber(ActionEvent event) throws IOException {
		Instance.getClientConsole().sendToServer(
				Command.getSubjNumber.ordinal() + "@" + filterCombo.getSelectionModel().getSelectedItem());
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");
		}
		Snumber = Instance.getClientConsole().getMessage().toString();
	}

	public void init(String username, String password) throws IOException {
		userString = username;
		paString = password;
		loadSubjects(username, password);
	}

	public void loadSubjects(String username, String password) throws IOException {
		Instance.getClientConsole().sendToServer(Command.teacherSubjects.ordinal() + "@" + username + "@" + password);
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");
		}
		String json = Instance.getClientConsole().getMessage().toString();
		List<String> ll = new ObjectMapper().readValue(json, ArrayList.class);
		filterCombo.getItems().removeAll(filterCombo.getItems());
		filterCombo.getItems().addAll(ll);
		filterCombo.getSelectionModel().select(0);
	}
}
