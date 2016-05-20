package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application implements Runnable{
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			scene.setOnMouseClicked(e -> System.out.println("full click completed"));
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("My Title");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new NetworkTablesReader().start();
		new MainController().start();
//		(new Thread(new Main())).start();
		launch(args);
	}

	
	@Override
	public void run() {
//		NetworkTable.setClientMode();
//		NetworkTable.setIPAddress("10.22.31.5");
//		NetworkTable table = NetworkTablesReader.table;
//		
//		System.out.println("table is ready");
//		while(true) {
//			SimpleBooleanProperty bool = NetworkTablesReader.bool;
//			if(bool != null) {
//				bool.setValue(table.getBoolean("left collector solenoid", false));
//			}
//		}
	}
	
}
