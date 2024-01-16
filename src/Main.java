import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	// Set up the scene and the stage
        primaryStage.setTitle("Simple Notepad");

        NoteTaking noteTaking = new NoteTaking();

        Scene scene = new Scene(noteTaking.getRoot(), 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
