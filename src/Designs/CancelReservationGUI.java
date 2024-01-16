package Designs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * @author mavhinamulisa
 * @version Airline Reservation System
 */

public class CancelReservationGUI {

    private Stage primaryStage;

    public CancelReservationGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setupCancelReservationScene() {
        GridPane cancelReservationGrid = new GridPane();
        cancelReservationGrid.setPadding(new Insets(20, 20, 20, 20));
        cancelReservationGrid.setVgap(10);
        cancelReservationGrid.setHgap(10);

        // Add UI components for Cancel Reservation here
        Label titleLabel = new Label("Cancel Reservation");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        cancelReservationGrid.add(titleLabel, 0, 0, 2, 1);

        // Ticket Number
        Label ticketNumberLabel = new Label("Enter Ticket Number:");
        TextField ticketNumberField = new TextField();
        cancelReservationGrid.add(ticketNumberLabel, 0, 1);
        cancelReservationGrid.add(ticketNumberField, 1, 1);

        Button cancelReservationButton = new Button("Cancel Reservation");
        cancelReservationButton.setOnAction(e -> handleCancellationConfirmation(ticketNumberField.getText()));

        cancelReservationGrid.add(cancelReservationButton, 0, 2, 2, 1);

        Scene cancelReservationScene = new Scene(cancelReservationGrid, 400, 200);
        primaryStage.setScene(cancelReservationScene);
        primaryStage.setTitle("Cancel Reservation");
    }

    //To allow user to cancel the reservation
    private void handleCancellationConfirmation(String studentNumber) {
        boolean isConfirmed = showCancelConfirmationDialog(studentNumber);

        if (isConfirmed) {
            boolean cancellationSuccess = cancelReservation(studentNumber);

            if (cancellationSuccess) {
                System.out.println("Reservation with Student Number " + studentNumber + " has been canceled.");
            } else {
                System.out.println("Failed to cancel reservation with Student Number " + studentNumber);
            }
        } else {
            // Cancellation was not confirmed
            System.out.println("Reservation cancellation was not confirmed.");
        }
    }
    
    // To access the database and delete the information 
    private boolean cancelReservation(String ticketNumber) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/airline_reservation", "root", "Mavhina123")) {
            String query = "DELETE FROM Passenger WHERE TicketNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ticketNumber);
                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean showCancelConfirmationDialog(String ticketNumber) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Cancel Reservation Confirmation");
        confirmationAlert.setHeaderText(null);

        confirmationAlert.setContentText("Are you sure you want to cancel the reservation with Ticket Number " + ticketNumber + "?");

        ButtonType confirmButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmationAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result.orElse(null) == confirmButton;
    }
}
