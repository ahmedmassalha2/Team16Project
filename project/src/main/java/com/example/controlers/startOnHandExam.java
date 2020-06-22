/**
 * Sample Skeleton for 'startOnHandExam.fxml' Controller Class
 */

package com.example.controlers;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.operations.generalOps;
import com.example.project.startApp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class startOnHandExam {
	static int mintsExam = 10;
	static int secondsExam = 20;
	@FXML // fx:id="idNum"
	private TextField idNum; // Value injected by FXMLLoader

	@FXML // fx:id="examCode"
	private TextField examCode; // Value injected by FXMLLoader

	@FXML // fx:id="submitExambtn"
	private Button submitExambtn; // Value injected by FXMLLoader
	@FXML // fx:id="enterExamBtn"
	private Button enterExamBtn; // Value injected by FXMLLoader

	@FXML // fx:id="backBtn"
	private Button backBtn; // Value injected by FXMLLoader

	@FXML // fx:id="minuts"
	private TextField minuts; // Value injected by FXMLLoader

	@FXML // fx:id="seconds"
	private TextField seconds; // Value injected by FXMLLoader
	@FXML // fx:id="errorTxt"
	private Text errorTxt; // Value injected by FXMLLoader
	private String exCode;
	static String duration = "";
	static String course = "";
	static String teacherId = "";

	@FXML
	void enterExam(ActionEvent event) throws IOException {
		exCode = examCode.getText();
		Instance.sendMessage(Command.getExamIdBycode.ordinal() + "@" + examCode.getText());
		Instance.sendMessage(
				Command.getExamById.ordinal() + "@" + Instance.getClientConsole().getMessage().toString() + "@onhand");
		String[] args = Instance.getClientConsole().getMessage().toString().split("@");
		duration = args[3];
		course = args[5];
		teacherId = args[12];

		FileChooser fc = new FileChooser();
		fc.setTitle("Download file");
		fc.setInitialFileName("myExam");// description:"Word file",_extensions:"*.doc"
		fc.getExtensionFilters().addAll(new ExtensionFilter("Word file", "*.docx"));
		File file = fc.showSaveDialog(null);
		PrintWriter p = new PrintWriter(file);
		p.write(args[0]);
		p.close();
		examTimer myTimer = new examTimer();
		Thread t = new Thread(myTimer);
		t.start();
		submitExambtn.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) throws IOException {
		FileChooser fc = new FileChooser();
		fc.setTitle("Download file");
		fc.setInitialFileName("myExam");// description:"Word file",_extensions:"*.doc"
		fc.getExtensionFilters().addAll(new ExtensionFilter("Word file", "*.docx"));
		File file = fc.showOpenDialog(null);
		String examDis = "Student ID: " + idNum.getText() + "\n" + "Duration: " + duration + "\n" + "Exam in: "
				+ course;
		System.out.println(examDis);
		try (Scanner scanner = new Scanner(file)) {
			String lines = "";
			while (scanner.hasNextLine()) {
				System.out.println("here ");
				lines += scanner.nextLine() + "\n";
			}
			List<String> exDis = new ArrayList<>();
			exDis.add(idNum.getText());
			exDis.add(duration);
			exDis.add(course);
			Instance.sendMessage(Command.submitHanedExam.ordinal() + "@" + teacherId + "@"
					+ generalOps.getJsonString(exDis) + "@" + lines);
			// String toSubmitString = "" + ;
			// System.out.println(toSubmitString);
			// byte[] bytes2 = new ObjectMapper().readValue(toSubmitString, byte[].class);
			goBack(event);
		}

		/*
		 * File someFile = new File("java3.doc"); FileOutputStream fos = new
		 * FileOutputStream(someFile); fos.write(bytes2); fos.flush(); fos.close();
		 */

	}

	@FXML
	void goBack(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentMainPage.fxml"));
		Scene scene = new Scene(loader.load());
		Stage Window = startApp.stageM;

		Window.setTitle("Main page");
		Window.setScene(scene);
		Window.show();
	}

	public void back() {
		secondsExam = 4;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project/StudentMainPage.fxml"));
		Scene scene;
		try {
			scene = new Scene(loader.load());
			Stage Window = startApp.stageM;

			Window.setTitle("Main page");
			Window.setScene(scene);
			Window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public class examTimer implements Runnable {
		int second;
		int mints;

		public examTimer() {
			this.second = secondsExam;
			this.mints = mintsExam;
		}

		@Override
		public void run() {
			while (true) {
				second--;
				secondsExam = second;
				seconds.setText(Integer.toString(second));
				minuts.setText(Integer.toString(mints));
				if (second <= 0) {
					mints--;
					mintsExam = mints;
					minuts.setText(Integer.toString(mints));
					if (mints < 58) {
						seconds.setText("00");
						minuts.setText("00");
						break;
					}
					second = 4;
					break;

				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Platform.runLater(() -> {
				back();

			});

		}

	}
}
