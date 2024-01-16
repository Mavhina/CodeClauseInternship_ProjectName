package Designs;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

/**
 * 
 * @author mavhinamulisa
 * @version Airline Reservation System
 */

public class TicketRecordsGUI extends Application {

    // JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/airline_reservation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mavhina123";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ticket Records");

        // Display an alert to prompt the user to enter the ticket number
        TextInputDialog enterTicketNumberDialog = new TextInputDialog();
        enterTicketNumberDialog.setTitle("Enter Ticket Number");
        enterTicketNumberDialog.setHeaderText("Please enter the ticket number:");

        // Get the result of the dialog (entered ticket number)
        Optional<String> ticketNumberResult = enterTicketNumberDialog.showAndWait();

        // If the user entered a ticket number, proceed to display the table
        if (ticketNumberResult.isPresent()) {
            String enteredTicketNumber = ticketNumberResult.get();
            System.out.println("Entered Ticket Number: " + enteredTicketNumber);

            // Retrieve information from the database based on the ticket number
            TicketRecord selectedTicket = retrieveTicketInformation(enteredTicketNumber);

            if (selectedTicket != null) {
                // Create a table to display ticket records
                TableView<TicketRecord> ticketTable = new TableView<>();
                ticketTable.setEditable(true);

                TableColumn<TicketRecord, String> departureCol = new TableColumn<>("Departure Airport");
                departureCol.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));

                TableColumn<TicketRecord, String> arrivalCol = new TableColumn<>("Arrival Airport");
                arrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));

                TableColumn<TicketRecord, LocalDate> departureDateCol = new TableColumn<>("Departure Date");
                departureDateCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));

                TableColumn<TicketRecord, LocalDate> returnDateCol = new TableColumn<>("Return Date");
                returnDateCol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

                TableColumn<TicketRecord, String> flightCol = new TableColumn<>("Flight");
                flightCol.setCellValueFactory(new PropertyValueFactory<>("flight"));

                TableColumn<TicketRecord, Integer> numOfPeopleCol = new TableColumn<>("Number of People");
                numOfPeopleCol.setCellValueFactory(new PropertyValueFactory<>("numOfPeople"));

                TableColumn<TicketRecord, String> passengerNamesCol = new TableColumn<>("Passenger Names");
                passengerNamesCol.setCellValueFactory(new PropertyValueFactory<>("passengerNames"));

                TableColumn<TicketRecord, String> contactInfoCol = new TableColumn<>("Contact Information");
                contactInfoCol.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

                TableColumn<TicketRecord, String> emailCol = new TableColumn<>("Email");
                emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

                ticketTable.getColumns().addAll(departureCol, arrivalCol, departureDateCol, returnDateCol, flightCol,
                        numOfPeopleCol, passengerNamesCol, contactInfoCol, emailCol);

                ObservableList<TicketRecord> data = FXCollections.observableArrayList(selectedTicket);
                ticketTable.setItems(data);

                GridPane ticketRecordsGrid = new GridPane();
                ticketRecordsGrid.setPadding(new Insets(20, 20, 20, 20));
                ticketRecordsGrid.setVgap(10);
                ticketRecordsGrid.setHgap(10);

                ticketRecordsGrid.add(ticketTable, 0, 0);

                Scene ticketRecordsScene = new Scene(ticketRecordsGrid, 800, 600);
                primaryStage.setScene(ticketRecordsScene);

                primaryStage.show();
            } else {
                System.out.println("Ticket not found in the database.");
            }
        } else {
            // User canceled entering the ticket number, you may handle this case accordingly
            System.out.println("User canceled entering the ticket number. Exiting application.");
        }
    }

    //To retrieve the ticket information from the database
    private TicketRecord retrieveTicketInformation(String ticketNumber) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM Passenger WHERE TicketNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, ticketNumber);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new TicketRecord(
                                resultSet.getString("DepartureAirport"),
                                resultSet.getString("ArrivalAirport"),
                                resultSet.getDate("DepartureDate").toLocalDate(),
                                resultSet.getDate("ReturnDate").toLocalDate(),
                                resultSet.getString("SelectedFlight"),
                                resultSet.getInt("NumberOfPeople"),
                                resultSet.getString("PassengerNames"),
                                resultSet.getString("ContactInfo"),
                                resultSet.getString("Email")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // A simple class representing a Ticket Record
    public static class TicketRecord {
        private String departureAirport;
        private String arrivalAirport;
        private LocalDate departureDate;
        private LocalDate returnDate;
        private String flight;
        private int numOfPeople;
        private String passengerNames;
        private String contactInfo;
        private String email;

        public TicketRecord(String departureAirport, String arrivalAirport, LocalDate departureDate,
                            LocalDate returnDate, String flight, int numOfPeople, String passengerNames,
                            String contactInfo, String email) {
            this.departureAirport = departureAirport;
            this.arrivalAirport = arrivalAirport;
            this.departureDate = departureDate;
            this.returnDate = returnDate;
            this.flight = flight;
            this.numOfPeople = numOfPeople;
            this.passengerNames = passengerNames;
            this.contactInfo = contactInfo;
            this.email = email;
        }

        // Add getters and setters as needed

        public String getDepartureAirport() {
            return departureAirport;
        }

        public String getArrivalAirport() {
            return arrivalAirport;
        }

        public LocalDate getDepartureDate() {
            return departureDate;
        }

        public LocalDate getReturnDate() {
            return returnDate;
        }

        public String getFlight() {
            return flight;
        }

        public int getNumOfPeople() {
            return numOfPeople;
        }

        public String getPassengerNames() {
            return passengerNames;
        }

        public String getContactInfo() {
            return contactInfo;
        }

        public String getEmail() {
            return email;
        }
    }
}
