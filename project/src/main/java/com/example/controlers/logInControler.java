/**
 * Sample Skeleton for 'logIN.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class logInControler implements Initializable {
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML // fx:id="userName"
	private TextField userName; // Value injected by FXMLLoader

	@FXML // fx:id="Password"
	private PasswordField Password; // Value injected by FXMLLoader

	@FXML // fx:id="logInBtn"
	private Button logInBtn; // Value injected by FXMLLoader
	@FXML // fx:id="selectionbtns"
	private ComboBox<String> selectionbtns; // Value injected by FXMLLoader
	@FXML // fx:id="errorTXT"
	private TextField errorTXT; // Value injected by FXMLLoader

	@FXML
	void logIn(ActionEvent event) throws IOException {
		Instance.getClientConsole().setMessage(null);
		String user = userName.getText();
		String paString = Password.getText();
		String tyString = selectionbtns.getSelectionModel().getSelectedItem();
		tyString = getClass(tyString);
		System.out.println(tyString);
		if (!checkValid(user, paString, tyString))
			return;

		String q = Command.accExist.ordinal() + "@" + user + "@" + paString + "@from @" + tyString
				+ "@ WHERE username = :username and password = :password";
		Instance.getClientConsole().sendToServer(q);
		while (Instance.getClientConsole().getMessage() == null) {
			System.out.println("waiting for server");

		}
		String reString = Instance.getClientConsole().getMessage().toString();
		if (reString.equals(""))
			errorTXT.setText("User not found");

		else {
			goTo(event, "/com/example/project/" + getController(tyString) + ".fxml", reString);
		}
	}

	private void goTo(ActionEvent event, String xmlPage, String data) {
		String[] commandArr = data.split("@");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(xmlPage));
			Parent Main = loader.load();
			if (loader.getController() instanceof StudentPageController) {
				StudentPageController secController = loader.getController();
				secController.init(commandArr[1]);
			}
			if (loader.getController() instanceof teacherMainPageController) {
				teacherMainPageController secController = loader.getController();
				secController.init(commandArr[1], commandArr[2]);
			}
			Scene scene = new Scene(Main);
			Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Window.getIcons().add(new Image("/com/example/project/images/uni_pic.jpg"));
			Window.setTitle(commandArr[0] + " page");
			Window.setScene(scene);
			Window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkValid(String user, String paString, String tyString) {
		errorTXT.setText("");
		if (user.isBlank()) {
			errorTXT.setText("Please fill user name");
			return false;
		}
		if (paString.isBlank()) {
			errorTXT.setText("Please fill password");
			return false;
		}
		if (tyString.equals("")) {
			errorTXT.setText("Select your role");
			return false;
		}
		return true;
	}

	public void loadData() {
		selectionbtns.getItems().removeAll(list);
		list.removeAll(list);
		list.add("Teacher");
		list.add("Student");
		list.add("Principal");
		selectionbtns.getItems().addAll(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();

	}

	private int isValid(String user, String paString) {
		/*
		 * String tyString = selectionbtns.getSelectionModel().getSelectedItem();
		 * tyString = getClass(tyString);
		 * 
		 * dataBase.getInstance(); Session session = dataBase.getSession(); String hql =
		 * "from " + "Teacher  where username = :username"; List result =
		 * session.createQuery(hql).setString("username", user).setString("password",
		 * paString).list();
		 */
		return 1;
	}

	private String getClass(String clas) {
		if (clas == null)
			return "";
		if (clas.equals("Teacher"))
			return "Teacher";
		else {
			if (clas.equals("Student"))
				return "Student";
			else {
				if (clas.equals("Principal"))
					return "Principal";
				return "";
			}
		}
	}

	private String getController(String type) {
		if (type.equals("Teacher"))
			return "teacherMainPage";
		if (type.equals("Student"))
			return "StudentMainPage";

		return "";
	}

}
