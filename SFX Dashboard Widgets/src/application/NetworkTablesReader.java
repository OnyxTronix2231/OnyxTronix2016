package application;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NetworkTablesReader extends Thread implements ITableListener {
	public static NetworkTable table;
	
	public void run() {
		NetworkTable.setClientMode();
		NetworkTable.setIPAddress("10.22.31.5");
		table = NetworkTable.getTable("costumDashboard");
		table.addTableListener(this);
		new MainController().start();
	}
	
	@Override
	public void valueChanged(ITable arg0, String string, Object o, boolean bool) {
	}
}