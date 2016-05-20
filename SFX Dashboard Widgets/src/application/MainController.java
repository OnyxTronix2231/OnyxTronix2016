package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MainController extends Thread {
	
	@FXML
	private Label myMessage;
	@FXML
	private Pane pistonRod;
	
	public static Label cheaterMessage;
	public static Pane cheaterRod;
	
	public void run() {
		while(true) {
			if(NetworkTablesReader.bool == null)
				continue;
			if(NetworkTablesReader.bool.get()) {
				Platform.runLater(new Thread() {
					@Override
					public void run() {
						hidePistonRod();
					}
				});
			} else {
				Platform.runLater(new Thread() {
					@Override
					public void run() {
						revealPistonRod();
					}
				});
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void hidePistonRod() {
		System.out.println(myMessage + "");
		if(myMessage != null && pistonRod != null) {
			System.out.println("piston hidden");
			myMessage.setText("piston hidden");
			pistonRod.setVisible(false);
			cheaterMessage = myMessage;
			cheaterRod = pistonRod;
		} else if(cheaterMessage != null && cheaterRod != null) {
			System.out.println("cheating ^^, piston hidden");
			cheaterMessage.setText("piston hidden");
			cheaterRod.setVisible(false);
		}
	}
	
	public void revealPistonRod() {
		System.out.println(myMessage + "");
		if(myMessage != null && pistonRod != null) {
			System.out.println("piston revealed");
			myMessage.setText("piston revealed");
			pistonRod.setVisible(true);
			cheaterMessage = myMessage;
			cheaterRod = pistonRod;
		} else if(cheaterMessage != null && cheaterRod != null) {
			System.out.println("cheating ^^, piston revealed");
			cheaterMessage.setText("piston revealed");
			cheaterRod.setVisible(true);
		}
	}
}
