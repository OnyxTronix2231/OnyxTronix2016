package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainController extends Thread {

	@FXML
	private Pane pistonRod;

	public static Pane publicRod;

	public void run() {
		while (true) {
			NetworkTablesReader.bool.setValue(NetworkTablesReader.table.getBoolean("left collector solenoid", false));
			if (NetworkTablesReader.bool == null)
				continue;
			if (NetworkTablesReader.bool.get()) {
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void hidePistonRod() {
		if (pistonRod != null) {
			pistonRod.setVisible(false);
			publicRod = pistonRod;
		} else if (publicRod != null) {
			publicRod.setVisible(false);
		}
	}

	public void revealPistonRod() {
		if (pistonRod != null) {
			pistonRod.setVisible(true);
			publicRod = pistonRod;
		} else if (publicRod != null) {
			publicRod.setVisible(true);
		}
	}
}
