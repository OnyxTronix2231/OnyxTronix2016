package application;

import javafx.scene.layout.Pane;

public class Piston implements Part {
	
	private String tableName;
	private boolean isClosed;
	private Pane rod;

	@Override
	public void update() {
		if(rod != null) {
			if(isClosed) {
				rod.setVisible(false);
			} else {
				rod.setVisible(true);
			}
		}
	}

	@Override
	public Object getState() {
		return isClosed;
	}

	@Override
	public void setState(Object isClosed) {
		try {
			this.isClosed = (boolean) isClosed;
		} catch (ClassCastException e) {}
	}
	
	public Pane getRod() {
		return rod;
	}

	public void setRod(Pane rod) {
		this.rod = rod;
	}

	@Override
	public String getTableName() {
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
