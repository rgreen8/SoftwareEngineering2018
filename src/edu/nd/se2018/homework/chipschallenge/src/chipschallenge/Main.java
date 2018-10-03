package chipschallenge;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	private Pane root;
	private Scene scene;
	@Override
	public void start(Stage stage) {
		try {
			root = new Pane();
			scene = new Scene(root,1000,1000);
			stage.setTitle("Chip's Challenge");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
