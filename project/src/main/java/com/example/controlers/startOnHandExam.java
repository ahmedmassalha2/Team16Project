/**
 * Sample Skeleton for 'startOnHandExam.fxml' Controller Class
 */

package com.example.controlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.ServerClientEntities.Command;
import com.example.ServerClientEntities.Instance;
import com.example.controlers.studentExamPageController.examTimer;
import com.example.project.startApp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class startOnHandExam {
	static int mintsExam = 10;
	static int secondsExam = 4;
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

	@FXML
	void enterExam(ActionEvent event) throws IOException {
		exCode = examCode.getText();
		Instance.sendMessage(Command.getExamIdBycode.ordinal() + "@" + examCode.getText());
		Instance.sendMessage(
				Command.getExamById.ordinal() + "@" + Instance.getClientConsole().getMessage().toString() + "@onhand");
		FileChooser fc = new FileChooser();
		fc.setTitle("Download file");
		fc.setInitialFileName("myExam");// description:"Word file",_extensions:"*.doc"
		fc.getExtensionFilters().addAll(new ExtensionFilter("Word file", "*.doc"));
		File file = fc.showSaveDialog(null);
		PrintWriter p = new PrintWriter(file);
		p.write(Instance.getClientConsole().getMessage().toString());
		p.close();
		examTimer myTimer = new examTimer();
		Thread t = new Thread(myTimer);
		t.start();
		submitExambtn.setVisible(true);
	}

	@FXML
	void submit(ActionEvent event) {
		System.out.println("1");
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
