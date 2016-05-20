package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HidePistonRod extends Thread{
	@FXML
	private Pane pistonRod;
	@FXML
	private Label myMessage;

	@Override
	public void run() {
		System.out.println("in HidePistonRod():15");
		System.out.println(pistonRod + ", " + myMessage);
		if(myMessage != null && pistonRod != null) {
			System.out.println("piston hidden");
			myMessage.setText("piston hidden");
			pistonRod.setVisible(false);
		}
	}
}
