import codeclause.resume.gui.ResumePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author mavhinamulisa
 * 
 */
public class Main extends Application{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		ResumePane root = new ResumePane();
		Scene sc = new Scene(root, 1070, 600);
		primaryStage.setTitle("Online Resume Builder");
		primaryStage.setScene(sc);
		primaryStage.show();
	}

}
