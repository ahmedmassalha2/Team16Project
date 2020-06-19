package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
	void enterExam(ActionEvent event) throws IOException, SQLException {

		Instance.sendMessage(
				Command.isStudentExistById.ordinal() + "@" + idNum.getText() + "@" + usrName + "@" + password);
		if (!(Instance.getClientConsole().getMessage().toString().equals("exist"))) {

			errorTxt.setText("Invalid ID or exam code");
		}

		else {
			Instance.sendMessage(Command.getExamCourseByCode.ordinal() + "@" + examCode.getText());
			String subject = Instance.getClientConsole().getMessage().toString();
			System.out.println("subjeect:  " + subject);
			Instance.sendMessage(Command.isStudentExistInCourse.ordinal() + "@" + idNum.getText() + "@" + subject);
			if (!Instance.getClientConsole().getMessage().toString().equals("exist")) {

				errorTxt.setText("Invalid ID or exam code");

			}

		}
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/studentExamPage.fxml"));
		Parent Main = loader.load();
		studentExamPageController secController = loader.getController();
		Instance.sendMessage(Command.getExamIdBycode.ordinal() + "@" + examCode.getText());
		Instance.sendMessage(Command.getExamById.ordinal() + "@" + Instance.getClientConsole().getMessage().toString());
		String dataString = Instance.getClientConsole().getMessage().toString();
		studentExamPageController.studentIDString = idNum.getText();
		Instance.sendMessage(Command.getNameByUsrName.ordinal() + "@" + usrName);
		studentExamPageController.studName = Instance.getClientConsole().getMessage().toString();
		secController.initByExam(dataString);
		Scene scene = new Scene(Main);
		Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Window.setTitle("Create exam main page");
		Window.setScene(scene);
		Window.show();

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
