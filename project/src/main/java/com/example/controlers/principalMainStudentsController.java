/**
 * Sample Skeleton for 'principalMainStudents.fxml' Controller Class
 */

package com.example.controlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class principalMainStudentsController implements Initializable {

	@FXML // fx:id="studentsList"
	private ListView<String> studentsList; // Value injected by FXMLLoader

	@FXML // fx:id="backBTN"
	private Button backBTN; // Value injected by FXMLLoader

	@FXML
	void back(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init() throws IOException {
		Instance.sendMessage(Command.getAllStudents.ordinal() + "");
		String dataString = Instance.getClientConsole().getMessage().toString();
		System.out.println("dataaaaaaaaa: " + dataString);
		String[] data = dataString.split("@");
		List<String> l = new ArrayList<>();
		for (String s : data)
			l.add(s);

		// studentsList = new ListView<>();
		studentsList.getItems().removeAll(studentsList.getItems());
		studentsList.getItems().addAll(l);
	}

}
