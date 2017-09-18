package net.horizoncode.ac;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AbductionCounterController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="progressBar"
	private JFXProgressBar progressBar; // Value injected by FXMLLoader

	@FXML // fx:id="abductionpossibleText"
	private Text abductionpossibleText; // Value injected by FXMLLoader

	@FXML // fx:id="runsCount"
	private Text runsCount; // Value injected by FXMLLoader

	@FXML // fx:id="clearedRunsButton"
	private JFXButton clearedRunsButton; // Value injected by FXMLLoader

	@FXML // fx:id="clearedAbductionsButton"
	private JFXButton clearedAbductionsButton; // Value injected by FXMLLoader

	@FXML // fx:id="clearedXHAbductionsButton"
	private JFXButton clearedXHAbductionsButton; // Value injected by FXMLLoader

	@FXML // fx:id="clearedRuns"
	private Text clearedRuns; // Value injected by FXMLLoader

	@FXML // fx:id="clearedAbductions"
	private Text clearedAbductions; // Value injected by FXMLLoader

	@FXML // fx:id="clearedXHAbductions"
	private Text clearedXHAbductions; // Value injected by FXMLLoader

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert progressBar != null : "fx:id=\"progressBar\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert abductionpossibleText != null : "fx:id=\"abductionpossibleText\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert runsCount != null : "fx:id=\"runsCount\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedRunsButton != null : "fx:id=\"clearedRunsButton\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedAbductionsButton != null : "fx:id=\"clearedAbductionsButton\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedXHAbductionsButton != null : "fx:id=\"clearedXHAbductionsButton\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedRuns != null : "fx:id=\"clearedRuns\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedAbductions != null : "fx:id=\"clearedAbductions\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		assert clearedXHAbductions != null : "fx:id=\"clearedXHAbductions\" was not injected: check your FXML file 'AbductionCounterScene.fxml'.";
		new Thread() {
			public void run() {
				try {
					sleep(5L);
				} catch (InterruptedException e) {
				}

				updateText();

				clearedRunsButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						int currentRunsUpdated = getClearedRuns() + 1;
						AbductionCounter.config.set(AbductionCounter.clearedRuns, currentRunsUpdated);
						updateText();
					}
				});

				clearedAbductionsButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						AbductionCounter.config.set(AbductionCounter.clearedRuns, 0);
						int currentAbductions = getClearedAbductions() + 1;
						AbductionCounter.config.set(AbductionCounter.clearedAbductions, currentAbductions);
						updateText();
					}
				});
				
				clearedXHAbductionsButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						AbductionCounter.config.set(AbductionCounter.clearedRuns, 0);
						int currentAbductions = getClearedAbductions() + 1;
						AbductionCounter.config.set(AbductionCounter.clearedAbductions, currentAbductions);
						int currentXHAbductions = getClearedXHAbductions() + 1;
						AbductionCounter.config.set(AbductionCounter.clearedXHAbductions, currentXHAbductions);
						updateText();
					}
				});

			};
		}.start();
	}

	public void updateText() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				clearedRuns.setText("Cleared Runs this Set: " + getClearedRuns());
				clearedAbductions.setText("Cleared Abductions: " + getClearedAbductions());
				clearedXHAbductions.setText("Cleared XH Abductions: " + getClearedXHAbductions());

				runsCount.setText(getClearedRuns() + "/15");

				float percent = (getClearedRuns() * 100.0f) / 15;
				System.out.println(percent / 100.0f);
				progressBar.setProgress(percent / 100.0f);
				if (getClearedRuns() >= 15) {
					abductionpossibleText.setText("A abduction is usually now possible!");
				} else {
					abductionpossibleText.setText("A abduction is USUALLY NOT possible at the moment!");
				}
				
				AbductionCounter.config.saveConfig();
			}
		});
	}

	public int getClearedRuns() {
		int currentRuns = 0;
		if (AbductionCounter.config.get(AbductionCounter.clearedRuns) instanceof String) {
			currentRuns = Integer.parseInt((String) AbductionCounter.config.get(AbductionCounter.clearedRuns));
		}
		if (AbductionCounter.config.get(AbductionCounter.clearedRuns) instanceof Integer) {
			currentRuns = (int) AbductionCounter.config.get(AbductionCounter.clearedRuns);
		}
		return currentRuns;
	}

	public int getClearedAbductions() {
		int currentRuns = 0;
		if (AbductionCounter.config.get(AbductionCounter.clearedAbductions) instanceof String) {
			currentRuns = Integer.parseInt((String) AbductionCounter.config.get(AbductionCounter.clearedAbductions));
		}
		if (AbductionCounter.config.get(AbductionCounter.clearedAbductions) instanceof Integer) {
			currentRuns = (int) AbductionCounter.config.get(AbductionCounter.clearedAbductions);
		}
		return currentRuns;
	}

	public int getClearedXHAbductions() {
		int currentRuns = 0;
		if (AbductionCounter.config.get(AbductionCounter.clearedXHAbductions) instanceof String) {
			currentRuns = Integer.parseInt((String) AbductionCounter.config.get(AbductionCounter.clearedXHAbductions));
		}
		if (AbductionCounter.config.get(AbductionCounter.clearedXHAbductions) instanceof Integer) {
			currentRuns = (int) AbductionCounter.config.get(AbductionCounter.clearedXHAbductions);
		}
		return currentRuns;
	}
}
