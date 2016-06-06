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
		bool = new SimpleBooleanProperty(false);
		new MainController().start();
	}
	
	@Override
	public void valueChanged(ITable arg0, String string, Object o, boolean bool) {
	}
}