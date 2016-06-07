package application;

import java.lang.reflect.Method;

public enum Part {
	leftCollectorSolenoid,rightCollectorSolenoid;
	
	private Method updateMethod;

	private Part () {
		try {
			this.updateMethod = MainController.class.getDeclaredMethod(this.name(), Object.class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	public Method getUpdateMethod() {
		return updateMethod;
	}
}
