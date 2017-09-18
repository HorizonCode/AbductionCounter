package net.horizoncode.ac;

import java.io.File;

import javax.swing.UIManager;

import org.apache.commons.lang3.SystemUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class was created by HorizonCode 
 * Create Date: 18.09.2017 / 21:13 
 * Project: AbductionCounter
 */
public class AbductionCounter extends Application {

	public Scene scene;
	public Stage stage;

	public static Config config;

	public static String clearedRuns = "cleared-runs";
	public static String clearedAbductions = "cleared-abductions";
	public static String clearedXHAbductions = "cleared-xhabductions";

	@Override
	public void start(Stage primaryStage) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("AbductionCounterScene.fxml"));

		File directory = new File(getAppData(), "AbductionCounter");
		if (!directory.exists()) {
			directory.mkdir();
		}

		File configFile = new File(directory, "data.cfg");
		config = Config.loadConfig(configFile);

		if (!config.isSet(clearedRuns))
			config.set(clearedRuns, 0);

		if (!config.isSet(clearedAbductions))
			config.set(clearedAbductions, 0);

		if (!config.isSet(clearedXHAbductions))
			config.set(clearedXHAbductions, 0);

		config.saveConfig();

		scene = new Scene(fxmlLoader.load());
		stage = new Stage();
		stage.setTitle("AbductionCounter");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		stage.centerOnScreen();
		stage.requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}

	static String getAppData() {
		if (SystemUtils.IS_OS_WINDOWS) {
			return (System.getenv("APPDATA"));
		}
		if (SystemUtils.IS_OS_MAC_OSX) {
			return (System.getProperty("user.home") + "/Library/Application Support/");
		}
		if (SystemUtils.IS_OS_LINUX) {
			return (System.getProperty("user.home") + "/");
		}
		return "";
	}
	
	@Override
	public void stop() throws Exception {
		config.saveConfig();
		super.stop();
	}

}
