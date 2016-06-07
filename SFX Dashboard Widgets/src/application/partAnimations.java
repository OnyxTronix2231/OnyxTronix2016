package application;

import javafx.scene.layout.Pane;

public class partAnimations {
	public static void piston (Pane rod, Boolean isClosed) {
		if(rod != null && isClosed != null) {
			if(isClosed) {
				rod.setVisible(false);
			} else {
				rod.setVisible(true);
			}
		}
	}
}
