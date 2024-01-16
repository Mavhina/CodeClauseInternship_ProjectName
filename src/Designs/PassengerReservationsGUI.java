package Designs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

/**
 * 
 * @author mavhinamulisa
 * @version Airline Reservation System
 */

public class PassengerReservationsGUI {

	//Global variables
    private Stage primaryStage;
    private double dblTotalPrice;
    private int intrandomNumber;

    public PassengerReservationsGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setupPassengerReservationsScene() {
        GridPane passengerReservationsGrid = new GridPane();
        passengerReservationsGrid.setPadding(new Insets(20, 20, 20, 20));
        passengerReservationsGrid.setVgap(10);
        passengerReservationsGrid.setHgap(10);

        //Add UI components for Passenger Reservations here
        Label titleLabel = new Label("Passenger Reservations");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        passengerReservationsGrid.add(titleLabel, 0, 0, 2, 1);

        //Travel Dates
        Label travelDatesLabel = new Label("Travel Dates:");
        DatePicker departureDatePicker = new DatePicker();
        DatePicker returnDatePicker = new DatePicker();
        passengerReservationsGrid.add(travelDatesLabel, 0, 1);
        passengerReservationsGrid.add(departureDatePicker, 1, 1);
        passengerReservationsGrid.add(new Label("Return Date:"), 0, 2);
        passengerReservationsGrid.add(returnDatePicker, 1, 2);

        //Departure Airport
        Label departureAirportLabel = new Label("Departure Airport:");
        ComboBox<String> departureAirportComboBox = new ComboBox<>();
        populateAirports(departureAirportComboBox);
        passengerReservationsGrid.add(departureAirportLabel, 0, 3);
        passengerReservationsGrid.add(departureAirportComboBox, 1, 3);

        //Arrival Airport
        Label arrivalAirportLabel = new Label("Arrival Airport:");
        ComboBox<String> arrivalAirportComboBox = new ComboBox<>();
        populateAirports(arrivalAirportComboBox);
        passengerReservationsGrid.add(arrivalAirportLabel, 0, 4);
        passengerReservationsGrid.add(arrivalAirportComboBox, 1, 4);

        //Flight Selection
        Label flightLabel = new Label("Select Flight:");
        ComboBox<String> flightComboBox = new ComboBox<>();
        populateFlights(flightComboBox);
        passengerReservationsGrid.add(flightLabel, 0, 5);
        passengerReservationsGrid.add(flightComboBox, 1, 5);

        //Number of People
        Label numOfPeopleLabel = new Label("Number of People:");
        ComboBox<Integer> numOfPeopleComboBox = new ComboBox<>();
        populateNumOfPeople(numOfPeopleComboBox);
        passengerReservationsGrid.add(numOfPeopleLabel, 0, 6);
        passengerReservationsGrid.add(numOfPeopleComboBox, 1, 6);

        //Passenger Information
        Label passengerInfoLabel = new Label("Passenger Information:");
        TextField passengerNamesField = new TextField();
        TextField contactInfoField = new TextField();
        TextField emailField = new TextField();
        passengerReservationsGrid.add(passengerInfoLabel, 0, 7);
        passengerReservationsGrid.add(new Label("Full Names:"), 0, 8);
        passengerReservationsGrid.add(passengerNamesField, 1, 8);
        passengerReservationsGrid.add(new Label("Contact Information:"), 0, 9);
        passengerReservationsGrid.add(contactInfoField, 1, 9);
        passengerReservationsGrid.add(new Label("Email:"), 0, 10);
        passengerReservationsGrid.add(emailField, 1, 10);

        Button reserveButton = new Button("Reserve Seat");
        reserveButton.setOnAction(e -> handleReservation(
                departureDatePicker.getValue(),
                returnDatePicker.getValue(),
                departureAirportComboBox.getValue(),
                arrivalAirportComboBox.getValue(),
                flightComboBox.getValue(),
                numOfPeopleComboBox.getValue(),
                passengerNamesField.getText(),
                contactInfoField.getText(),
                emailField.getText()));

        passengerReservationsGrid.add(reserveButton, 0, 11, 2, 1);

        Scene passengerReservationsScene = new Scene(passengerReservationsGrid, 550, 450);
        primaryStage.setScene(passengerReservationsScene);
        primaryStage.setTitle("Passenger Reservations");

        //Random number generate to generate a random 6 digits tickets number
        Random rand = new Random();
        intrandomNumber = 0;

        for (int i = 0; i < 6; i++) {
            intrandomNumber = intrandomNumber * 10 + rand.nextInt(10);
        }
    }

    //Function to populate the airport options
    private void populateAirports(ComboBox<String> airportComboBox) {
        airportComboBox.getItems().addAll("Cape Town International Airport", "OR Tambo International Airport", "King Shaka International Airport", "Lanseria International Airport");
    }

    //Function to populate the flights options
    private void populateFlights(ComboBox<String> flightComboBox) {
        flightComboBox.getItems().addAll("Flight 101", "Flight 202", "Flight 303", "Flight 404");
    }

    //Function to populate the number of people options
    private void populateNumOfPeople(ComboBox<Integer> numOfPeopleComboBox) {
        for (int i = 1; i <= 5; i++) {
            numOfPeopleComboBox.getItems().add(i);
        }
    }
    
    //To save the information to the database
    private void saveToDatabase(
            LocalDate departureDate,
            LocalDate returnDate,
            String departureAirport,
            String arrivalAirport,
            String selectedFlight,
            int numOfPeople,
            String passengerNames,
            String contactInfo,
            String email,
            double totalPrice,
            int ticketNumber) {

        String url = "jdbc:mysql://localhost:3306/airline_reservation";
        String username = "root";
        String password = "Mavhina123";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false); // Disable auto-commit for manual transaction control

            String sql = "INSERT INTO Passenger (TicketNumber, DepartureDate, ReturnDate, DepartureAirport, ArrivalAirport, " +
                    "SelectedFlight, NumberOfPeople, PassengerNames, ContactInfo, Email, Price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, ticketNumber);
                preparedStatement.setDate(2, java.sql.Date.valueOf(departureDate));
                preparedStatement.setDate(3, java.sql.Date.valueOf(returnDate));
                preparedStatement.setString(4, departureAirport);
                preparedStatement.setString(5, arrivalAirport);
                preparedStatement.setString(6, selectedFlight);
                preparedStatement.setInt(7, numOfPeople);
                preparedStatement.setString(8, passengerNames);
                preparedStatement.setString(9, contactInfo);
                preparedStatement.setString(10, email);
                preparedStatement.setDouble(11, totalPrice);

                preparedStatement.executeUpdate();
            }

            connection.commit(); // Commit the transaction

            System.out.println("Reservation information saved to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback(); // Roll back changes if an exception occurs
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close(); // Close the connection in the finally block
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }

    // Handles the reservation and calculating the total prices
    private void handleReservation(
            LocalDate departureDate,
            LocalDate returnDate,
            String departureAirport,
            String arrivalAirport,
            String selectedFlight,
            int numOfPeople,
            String passengerNames,
            String contactInfo,
            String email) {

        // Calculate the price here based on the selected airports
        if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("Cape Town International Airport") && arrivalAirport.equals("OR Tambo International Airport")) ||
                        (departureAirport.equals("OR Tambo International Airport") && arrivalAirport.equals("Cape Town International Airport")))) {
            dblTotalPrice = 1200 * numOfPeople;
        } else if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("Cape Town International Airport") && arrivalAirport.equals("King Shaka International Airport")) ||
                        (departureAirport.equals("King Shaka International Airport") && arrivalAirport.equals("Cape Town International Airport")))) {
            dblTotalPrice = 1000 * numOfPeople;
        } else if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("Cape Town International Airport") && arrivalAirport.equals("Lanseria International Airport")) ||
                        (departureAirport.equals("Lanseria International Airport") && arrivalAirport.equals("Cape Town International Airport")))) {
            dblTotalPrice = 500 * numOfPeople;
        } else if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("OR Tambo International Airport") && arrivalAirport.equals("King Shaka International Airport")) ||
                        (departureAirport.equals("King Shaka International Airport") && arrivalAirport.equals("OR Tambo International Airport")))) {
            dblTotalPrice = 2000 * numOfPeople;
        } else if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("OR Tambo International Airport") && arrivalAirport.equals("Lanseria International Airport")) ||
                        (departureAirport.equals("Lanseria International Airport") && arrivalAirport.equals("OR Tambo International Airport")))) {
            dblTotalPrice = 2500 * numOfPeople;
        } else if ((departureAirport != null && arrivalAirport != null) &&
                ((departureAirport.equals("King Shaka International Airport") && arrivalAirport.equals("Lanseria International Airport")) ||
                        (departureAirport.equals("Lanseria International Airport") && arrivalAirport.equals("King Shaka International Airport")))) {
            dblTotalPrice = 1800 * numOfPeople;
        }

        // Create an Alert to display the reservation information
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Reservation Confirmation");
        confirmationAlert.setHeaderText("NB: Please Write down your Ticket Number as it is used for all Inquaries");

        confirmationAlert.setContentText(
                "Departure Date: " + departureDate + "\n" +
                        "Return Date: " + returnDate + "\n" +
                        "Departure Airport: " + departureAirport + "\n" +
                        "Arrival Airport: " + arrivalAirport + "\n" +
                        "Selected Flight: " + selectedFlight + "\n" +
                        "Number of People: " + numOfPeople + "\n" +
                        "Passenger Names: " + passengerNames + "\n" +
                        "Contact Information: " + contactInfo + "\n" +
                        "Email: " + email + "\n" +
                        "Price: R" + dblTotalPrice + "\n" +
                        "Ticket No: "+ intrandomNumber);

        confirmationAlert.showAndWait();

        //Save to Database
        saveToDatabase(departureDate, returnDate, departureAirport, arrivalAirport, selectedFlight,
                numOfPeople, passengerNames, contactInfo, email, dblTotalPrice, intrandomNumber);
    }
}
