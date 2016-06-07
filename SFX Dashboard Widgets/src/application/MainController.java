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
								Object obj = this;
								Object value = NetworkTablesReader.table.getValue(part.name(), null);
								part.getUpdateMethod().invoke(obj, value);
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
	
	public void hidePistonRod() {
		if (pistonRod != null) {
			pistonRod.setVisible(false);
			publicRod = pistonRod;
		}
	}
	
	public static void leftCollectorSolenoid (Object isClosed) {
		partAnimations.piston(publicRod,(Boolean) isClosed);
	}
	
	public static void rightCollectorSolenoid (Object isClosed) {
		partAnimations.piston(null, (Boolean) isClosed);
	}
}
