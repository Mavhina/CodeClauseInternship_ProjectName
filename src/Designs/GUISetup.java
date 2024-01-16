package Designs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUISetup {

    private Stage primaryStage;

    public GUISetup(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setupMainScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");

        // Menu Items
        MenuItem passengerReservationsItem = new MenuItem("Passenger Reservations");
        MenuItem ticketRecordsItem = new MenuItem("Ticket Records");
        MenuItem cancelReservationItem = new MenuItem("Cancel Reservation");
        MenuItem updateTicketInfoItem = new MenuItem("Update Ticket Information");

        menu.getItems().addAll(
                passengerReservationsItem,
                ticketRecordsItem,
                cancelReservationItem,
                updateTicketInfoItem
        );

        // Handle menu item actions (add functionality as needed)
        passengerReservationsItem.setOnAction(e -> showPassengerReservations());
        ticketRecordsItem.setOnAction(e -> showTicketRecordsGUI());
        cancelReservationItem.setOnAction(e -> showCancelReservation());
        updateTicketInfoItem.setOnAction(e -> showUpdateTicketInfo());

        menuBar.getMenus().add(menu);

        // Add menu bar to the grid
        grid.add(menuBar, 0, 0, 2, 1);

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
    }

    private void showPassengerReservations() {
        PassengerReservationsGUI passengerReservationsGUI = new PassengerReservationsGUI(primaryStage);
        passengerReservationsGUI.setupPassengerReservationsScene();
    }

    private void showTicketRecordsGUI() {
        TicketRecordsGUI ticketRecordsGUI = new TicketRecordsGUI();
        ticketRecordsGUI.start(new Stage());
    }

    private void showCancelReservation() {
        CancelReservationGUI cancelReservationGUI = new CancelReservationGUI(primaryStage);
        cancelReservationGUI.setupCancelReservationScene();
    }

    private void showUpdateTicketInfo() {
        UpdateReservationGUI updateReservationGUI = new UpdateReservationGUI(primaryStage);
        updateReservationGUI.setupUpdateReservationScene();
    }
}
