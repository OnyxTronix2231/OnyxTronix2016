package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class RevealPistonRod extends Thread{
	@FXML
	private Pane pistonRod;
	@FXML
	private Label myMessage;

	@Override
	public void run() {
		System.out.println("in RevealPistonRod():15");
		System.out.println(pistonRod + ", " + myMessage);
		
		if(myMessage != null && pistonRod != null) {
			System.out.println();
			System.out.println("piston revealed");
			myMessage.setText("piston revealed");
			pistonRod.setVisible(true);
		}
	}
}
