/**
 * Sample Skeleton for 'logIN.fxml' Controller Class
 */

package com.example.controlers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import com.example.entities.Principal;
import com.example.entities.Student;
import com.example.entities.Teacher;
import com.example.project.dataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class logInControler implements Initializable {
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML // fx:id="userName"
	private TextField userName; // Value injected by FXMLLoader

	@FXML // fx:id="Password"
	private TextField Password; // Value injected by FXMLLoader

	@FXML // fx:id="logInBtn"
	private Button logInBtn; // Value injected by FXMLLoader
	@FXML // fx:id="selectionbtns"
	private ChoiceBox<String> selectionbtns; // Value injected by FXMLLoader

	@FXML
	void logIn(ActionEvent event) {
		String tyString = selectionbtns.getSelectionModel().getSelectedItem();
		tyString = getClass(tyString);
		String user = userName.getText();
		String paString = Password.getText();
		dataBase.getInstance();
		Session session = dataBase.getSession();

		Query query = session
				.createQuery("from "+ tyString+ " WHERE username = :username and password = :password");
		query.setParameter("username", user);
		query.setParameter("password", paString);
		List list = query.list();
		System.out.println(list.size());

	}

	public void loadData() {
		selectionbtns.getItems().removeAll(list);
		list.removeAll(list);
		list.add("Teacher");
		list.add("Student");
		list.add("principal");
		selectionbtns.getItems().addAll(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadData();

	}

	private int isValid(String user, String paString) {
		String tyString = selectionbtns.getSelectionModel().getSelectedItem();
		tyString = getClass(tyString);

		dataBase.getInstance();
		Session session = dataBase.getSession();
		String hql = "from " + "Teacher  where username = :username";
		List result = session.createQuery(hql).setString("username", user).setString("password", paString).list();

		return 1;
	}

	private String getClass(String clas) {
		if (clas.equals("Teacher"))
			return "Teacher";
		else {
			if (clas.equals("Student"))
				return "Student";
			else {
				return "Principal";
			}
		}
	}

}
