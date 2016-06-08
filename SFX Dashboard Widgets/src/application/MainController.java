package application;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainController extends Thread {

	@FXML
	private Pane pistonRod;
	private static Piston leftCollectorPiston;

	public void run() {
		leftCollectorPiston= new Piston();
		ArrayList<Part> parts = new ArrayList<Part>();
		parts.add(leftCollectorPiston);
		while (true) {
			for(Part part : parts) {
				if(NetworkTablesReader.table.containsKey(part.getTableName())) {
					Platform.runLater(new Thread() {
						@Override
						public void run() {
							part.setState(NetworkTablesReader.table.getValue(part.getTableName(), false));
							part.update();
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
			leftCollectorPiston.setRod(pistonRod);
		}
	}
}
