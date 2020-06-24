package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.entities.Exam;
import com.example.entities.Teacher;
import com.example.project.dataBase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExamCodeGenerateController implements Initializable {

	String usrName = "";
	String password = "";
	String examCode = "";
	@FXML // fx:id="codeBtn"
	private Button codeBtn; // Value injected by FXMLLoader
	@FXML // fx:id="codeIdText"
	private Text codeIdText; // Value injected by FXMLLoader
	@FXML // fx:id="backBtn"
	private Button backBtn; // Value injected by FXMLLoader
	@FXML // fx:id="onHandBTN"
	private Button onHandBTN; // Value injected by FXMLLoader
	@FXML // fx:id="extendBTN"
	private Button extendBTN; // Value injected by FXMLLoader

	@FXML // fx:id="timeExtentios"
	private TextField timeExtentios; // Value injected by FXMLLoader

	@FXML // fx:id="Explain"
	private TextArea Explain; // Value injected by FXMLLoader
	@FXML // fx:id="errorTXT"
	private Text errorTXT; // Value injected by FXMLLoader

	@FXML
	void examExtension(ActionEvent event) throws IOException {
		if (timeExtentios.getText().isBlank()) {
			errorTXT.setText("Enter needed time");
			return;
		}
		if (Explain.getText().isBlank()) {
			errorTXT.setText("Enter Explaination");
			return;
		}
		String query = "Exam code number:" + examCode + "\nTeacher: " + usrName + "\nTime: " + timeExtentios.getText()
				+ "\nExplain:\n" + Explain.getText();
		Instance.sendMessage(Command.EXTENDEX.ordinal()+"@"+query);

	}

	@FXML
	void goBack(ActionEvent event) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/teacherExamsList.fxml"));
		Parent Main = loader.load();
		teacherExamList secController = loader.getController();

		secController.init(usrName, password);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exams list");
		Window.setScene(scene);
		Window.show();

	}

	@FXML
	void generateCode(ActionEvent event) throws IOException {
		codeIdText.setText(examCode);
		String examNum = "" + examCode.charAt(2) + "" + examCode.charAt(3);
		Instance.sendMessage(Command.setExamByExamNum.ordinal() + "@" + examNum + "@" + examCode + "@" + usrName);

	}

	@FXML
	void generateCodeMan(ActionEvent event) throws IOException {
		codeIdText.setText(examCode);
		String examNum = "" + examCode.charAt(2) + "" + examCode.charAt(3);
		Instance.sendMessage(
				Command.setExamByExamNum.ordinal() + "@" + examNum + "@" + examCode + "@" + usrName + "@onhand");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void init(String examCode, String usrName, String password) {

		this.usrName = usrName;
		this.password = password;
		System.out.println("code : " + examCode);
		this.examCode = examCode;

	}

}
