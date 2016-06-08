package application;

public interface Part {
	public  void update();
	public String getTableName();
	public void setTableName(String tableName);
	public Object getState();
	public void setState(Object isClosed);
}
