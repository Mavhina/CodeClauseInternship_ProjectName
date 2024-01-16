import Designs.GUISetup;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author mavhinamulisa
 * @version Airline Reservation System
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Airline Reservation System");

        //Create an instance of GUISetup to set up the GUI
        GUISetup guiSetup = new GUISetup(primaryStage);
        guiSetup.setupMainScene();

        primaryStage.show();
    }
}
