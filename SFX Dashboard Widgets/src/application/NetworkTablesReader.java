package application;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;

public class NetworkTablesReader extends Thread implements ITableListener {
	@FXML
	public static SimpleBooleanProperty bool;
	public static NetworkTable table;
	
	public void run() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.22.31.5");
		table = NetworkTable.getTable("costumDashboard");
		table.addTableListener(this);
		System.out.println("table is ready");
		
		bool = new SimpleBooleanProperty(false);
		while(true) {
			try {
				bool.setValue(table.getBoolean("left collector solenoid", false));
//				System.out.println("solenoid value: " + bool);
				Thread.sleep(100);
				
			} catch (InterruptedException ex) {
				Logger.getLogger(NetworkTablesReader.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	@Override
	public void valueChanged(ITable arg0, String string, Object o, boolean bool) {
		System.out.println("value changed: " + string + ", " + o + ", " + bool);
//		 if(bool) {
//			 Platform.runLater(new RevealPistonRod());
//		 } else {
//			 Platform.runLater(new HidePistonRod());
//		 }
	}
}