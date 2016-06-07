package application;

import java.lang.reflect.InvocationTargetException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainController extends Thread {

	@FXML
	private Pane pistonRod;

	public static Pane publicRod;

	public void run() {
		while (true) {
			for(Part part : Part.values()) {
				if(NetworkTablesReader.table.containsKey(part.name())) {
					Platform.runLater(new Thread() {
						@Override
						public void run() {
							try {
								part.getUpdateMethod().invoke(this, NetworkTablesReader.table.getValue(part.name(), null));
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void leftCollectorSolenoid (Boolean isClosed) {
		partAnimations.piston(publicRod, isClosed);
	}
}
