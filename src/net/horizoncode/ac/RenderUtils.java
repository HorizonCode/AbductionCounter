package net.horizoncode.ac;


import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RenderUtils {
	
	public static void renderExitDialog(StackPane stackpane, String header, String text) {
		JFXDialogLayout content = new JFXDialogLayout();
    	content.setHeading(new Text(header));
    	content.setBody(new Text(text));
    	stackpane.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0, 1), 10, 0, 0, 0);");
    	content.setStyle("-fx-text-fill:WHITE;-fx-background-color:#ecf0f1;-fx-font-size:14px;-fx-font-family:system;");
    	JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.CENTER);
    	List<Node> actions = new ArrayList<Node>();
    	JFXButton abortButton = new JFXButton("Cancel");
    	abortButton.setStyle("-fx-background-color: #1481d9;-fx-font-size:13px;-fx-translate-x:13");
    	abortButton.setTextFill(Color.WHITE);
    	abortButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
    	actions.add(abortButton);
    	JFXButton closeButton = new JFXButton("Reset");
    	closeButton.setStyle("-fx-background-color: #e83333;-fx-font-size:13px;-fx-translate-x:15");
    	closeButton.setTextFill(Color.WHITE);
    	closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				AbductionCounter.config.set(AbductionCounter.clearedRuns, 0);
				AbductionCounter.config.set(AbductionCounter.clearedAbductions, 0);
				AbductionCounter.config.set(AbductionCounter.clearedXHAbductions, 0);
				AbductionCounterController.controllerInstance.updateText();
			}
		});
    	actions.add(closeButton);
    	content.setActions(actions);
    	dialog.show();
	}
}
