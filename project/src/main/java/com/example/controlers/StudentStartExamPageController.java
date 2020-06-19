package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentStartExamPageController implements Initializable {

	private String usrName = "";
	private String password = "";
	@FXML // fx:id="idNum"
	private TextField idNum; // Value injected by FXMLLoader

	@FXML // fx:id="examCode"
	private TextField examCode; // Value injected by FXMLLoader

	@FXML // fx:id="enterExamBtn"
	private Button enterExamBtn; // Value injected by FXMLLoader

	@FXML // fx:id="backBtn"
	private Button backBtn; // Value injected by FXMLLoader
	@FXML // fx:id="errorTxt"
	private Text errorTxt; // Value injected by FXMLLoader

	@FXML
	void enterExam(ActionEvent event) throws IOException {

		Instance.sendMessage(Command.isStudentExistById.ordinal() + "@" + idNum.getText()
								+ "@" + usrName + "@" + password);
		if (!(Instance.getClientConsole().getMessage().toString().equals("exist"))) {

			errorTxt.setText("Invalid ID or exam code");
		}

		else {
			Instance.sendMessage(Command.getExamCourseByCode.ordinal() + "@" + examCode.getText());
			String subject = Instance.getClientConsole().getMessage().toString();
			Instance.sendMessage(Command.isStudentExistInCourse.ordinal() + "@" + idNum.getText() + "@" + subject);
			if (!Instance.getClientConsole().getMessage().toString().equals("exist")) {

				errorTxt.setText("Invalid ID or exam code");
				

			}

		}
		

	}

	@FXML
	void goBack(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Exams list");
		Window.setScene(scene);
		Window.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void init(String usrName, String password) {
		
		this.usrName = usrName;
		this.password = password;

	}
}
