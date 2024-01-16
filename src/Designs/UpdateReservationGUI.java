package Designs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

/**
 * 
 * @author mavhinamulisa
 * @version Airline Reservation System
 */
public class UpdateReservationGUI {

	// Global variables
    private Stage primaryStage;
    private DatePicker departureDatePicker;
    private DatePicker returnDatePicker;
    private TextField passengerNamesField;
    private ComboBox<String> departureAirportComboBox;
    private ComboBox<String> arrivalAirportComboBox;
    private ComboBox<String> flightComboBox;
    private ComboBox<Integer> numOfPeopleComboBox;
    private TextField ticketNumberField;
    
    public UpdateReservationGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setupUpdateReservationScene() {
        GridPane updateReservationGrid = new GridPane();
        updateReservationGrid.setPadding(new Insets(20, 20, 20, 20));
        updateReservationGrid.setVgap(10);
        updateReservationGrid.setHgap(10);


        // Ticket Number
        Label ticketNumberLabel = new Label("Enter Ticket Number:");
        ticketNumberField = new TextField();
        updateReservationGrid.add(ticketNumberLabel, 0, 0);
        updateReservationGrid.add(ticketNumberField, 1, 0);

        Button checkTicketButton = new Button("Check Ticket");
        checkTicketButton.setOnAction(e -> handleCheckTicket(ticketNumberField.getText(),
                departureDatePicker, returnDatePicker, departureAirportComboBox, arrivalAirportComboBox,
                flightComboBox, numOfPeopleComboBox));

        updateReservationGrid.add(checkTicketButton, 2, 0);

        // Travel Dates
        Label travelDatesLabel = new Label("Travel Dates:");
        departureDatePicker = new DatePicker();
        returnDatePicker = new DatePicker();
        updateReservationGrid.add(travelDatesLabel, 0, 1);
        updateReservationGrid.add(departureDatePicker, 1, 1);
        updateReservationGrid.add(new Label("Return Date:"), 0, 2);
        updateReservationGrid.add(returnDatePicker, 1, 2);

        // Departure Airport
        Label departureAirportLabel = new Label("Departure Airport:");
        departureAirportComboBox = new ComboBox<>();
        populateAirports(departureAirportComboBox);
        updateReservationGrid.add(departureAirportLabel, 0, 3);
        updateReservationGrid.add(departureAirportComboBox, 1, 3);

        // Arrival Airport
        Label arrivalAirportLabel = new Label("Arrival Airport:");
        arrivalAirportComboBox = new ComboBox<>();
        populateAirports(arrivalAirportComboBox);
        updateReservationGrid.add(arrivalAirportLabel, 0, 4);
        updateReservationGrid.add(arrivalAirportComboBox, 1, 4);

        // Flight Selection
        Label flightLabel = new Label("Select Flight:");
        flightComboBox = new ComboBox<>();
        populateFlights(flightComboBox);
        updateReservationGrid.add(flightLabel, 0, 5);
        updateReservationGrid.add(flightComboBox, 1, 5);

        // Number of People
        Label numOfPeopleLabel = new Label("Number of People:");
        numOfPeopleComboBox = new ComboBox<>();
        populateNumOfPeople(numOfPeopleComboBox);
        updateReservationGrid.add(numOfPeopleLabel, 0, 6);
        updateReservationGrid.add(numOfPeopleComboBox, 1, 6);

        // Passenger Information
        Label passengerInfoLabel = new Label("Passenger Information:");
        passengerNamesField = new TextField();
        updateReservationGrid.add(passengerInfoLabel, 0, 7);
        updateReservationGrid.add(new Label("Full Names:"), 0, 8);
        updateReservationGrid.add(passengerNamesField, 1, 8);

        Button updateButton = new Button("Update Reservation");
        updateButton.setOnAction(e -> handleUpdateReservation(
                departureDatePicker.getValue(),
                returnDatePicker.getValue(),
                departureAirportComboBox.getValue(),
                arrivalAirportComboBox.getValue(),
                flightComboBox.getValue(),
                numOfPeopleComboBox.getValue()));

        updateReservationGrid.add(updateButton, 0, 9, 2, 1);

        Scene updateReservationScene = new Scene(updateReservationGrid, 450, 550);
        primaryStage.setScene(updateReservationScene);
        primaryStage.setTitle("Update Reservation");
    }

    private void populateAirports(ComboBox<String> airportComboBox) {
        airportComboBox.getItems().addAll("Cape Town International Airport", "OR Tambo International Airport", "King Shaka International Airport", "Lanseria International Airport");
    }

    private void populateFlights(ComboBox<String> flightComboBox) {
        flightComboBox.getItems().addAll("Flight 101", "Flight 202", "Flight 303", "Flight 404");
    }

    private void populateNumOfPeople(ComboBox<Integer> numOfPeopleComboBox) {
        for (int i = 1; i <= 5; i++) {
            numOfPeopleComboBox.getItems().add(i);
        }
    }

    private void handleCheckTicket(String ticketNumber,
                                   DatePicker departureDatePicker,
                                   DatePicker returnDatePicker,
                                   ComboBox<String> departureAirportComboBox,
                                   ComboBox<String> arrivalAirportComboBox,
                                   ComboBox<String> flightComboBox,
                                   ComboBox<Integer> numOfPeopleComboBox) {

        ReservationDetails reservationDetails = getReservationDetailsFromDatabase(ticketNumber);

        if (reservationDetails != null) {
            // Populate the fields with data from the database
            departureDatePicker.setValue(reservationDetails.getDepartureDate());
            returnDatePicker.setValue(reservationDetails.getReturnDate());
            departureAirportComboBox.setValue(reservationDetails.getDepartureAirport());
            arrivalAirportComboBox.setValue(reservationDetails.getArrivalAirport());
            flightComboBox.setValue(reservationDetails.getSelectedFlight());
            numOfPeopleComboBox.setValue(reservationDetails.getNumOfPeople());
            passengerNamesField.setText(reservationDetails.getPassengerNames());
        } else {
            // Show an alert if the ticket number is not found in the database
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ticket number not found!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void handleUpdateReservation(
            LocalDate departureDate,
            LocalDate returnDate,
            String departureAirport,
            String arrivalAirport,
            String selectedFlight,
            int numOfPeople) {
    	
        // Implement update reservation logic here
        System.out.println("Updating Reservation...");
        System.out.println("Departure Date: " + departureDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Departure Airport: " + departureAirport);
        System.out.println("Arrival Airport: " + arrivalAirport);
        System.out.println("Selected Flight: " + selectedFlight);
        System.out.println("Number of People: " + numOfPeople);
        System.out.println("Passenger Names: " + passengerNamesField.getText());

        // Update the information in the database
        updateReservationInDatabase(
                departureDate,
                returnDate,
                departureAirport,
                arrivalAirport,
                selectedFlight,
                numOfPeople,
                passengerNamesField.getText());
    }
    
    private void updateReservationInDatabase(
            LocalDate departureDate,
            LocalDate returnDate,
            String departureAirport,
            String arrivalAirport,
            String selectedFlight,
            int numOfPeople,
            String passengerNames) {
        // Connect the database and execute the update query
        // Replace the database connection details and query with your actual values

        try {
            String url = "jdbc:mysql://localhost:3306/airline_reservation";
            String username = "root";
            String password = "Mavhina123";
            Connection connection = DriverManager.getConnection(url, username, password);

            String updateQuery = "UPDATE Passenger SET " +
                    "DepartureDate = ?, " +
                    "ReturnDate = ?, " +
                    "DepartureAirport = ?, " +
                    "ArrivalAirport = ?, " +
                    "SelectedFlight = ?, " +
                    "NumberOfPeople = ?, " +
                    "PassengerNames = ? " +
                    "WHERE TicketNumber = ?";

            try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                // Set the parameters for the update query
                statement.setDate(1, Date.valueOf(departureDate));
                statement.setDate(2, Date.valueOf(returnDate));
                statement.setString(3, departureAirport);
                statement.setString(4, arrivalAirport);
                statement.setString(5, selectedFlight);
                statement.setInt(6, numOfPeople);
                statement.setString(7, passengerNames);
                
                // Set the ticket number as the last parameter
                statement.setString(8, ticketNumberField.getText()); // Assuming you have a ticketNumberField

                // Execute the update query
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Reservation updated successfully!");
                    // Display an alert indicating the successful update
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Reservation updated successfully!", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    System.out.println("Failed to update reservation. Ticket number not found.");
                    // Display an alert indicating the failure to update
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to update reservation. Ticket number not found.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private ReservationDetails getReservationDetailsFromDatabase(String ticketNumber) {
        // Connect to your database and retrieve reservation details based on the ticket number
        // Replace the database connection details and query with your actual values

        try {
            String url = "jdbc:mysql://localhost:3306/airline_reservation";
            String username = "root";
            String password = "Mavhina123";
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT DepartureDate, ReturnDate, DepartureAirport, ArrivalAirport, " +
                    "SelectedFlight, NumberOfPeople, PassengerNames FROM Passenger WHERE TicketNumber = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, ticketNumber);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve data from the result set and create a ReservationDetails object
                        LocalDate departureDate = resultSet.getDate("DepartureDate").toLocalDate();
                        LocalDate returnDate = resultSet.getDate("ReturnDate").toLocalDate();
                        String departureAirport = resultSet.getString("DepartureAirport");
                        String arrivalAirport = resultSet.getString("ArrivalAirport");
                        String selectedFlight = resultSet.getString("SelectedFlight");
                        int numOfPeople = resultSet.getInt("NumberOfPeople");
                        String passengerNames = resultSet.getString("PassengerNames");

                        return new ReservationDetails(departureDate, returnDate, departureAirport,
                                arrivalAirport, selectedFlight, numOfPeople, passengerNames);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the ticket number is not found or an error occurs
    }

      
    /**
     * 
     * @author mavhinamulisa
     * ReservationDetails class
     */
    public class ReservationDetails {

        private LocalDate departureDate;
        private LocalDate returnDate;
        private String departureAirport;
        private String arrivalAirport;
        private String selectedFlight;
        private int numOfPeople;
        private String passengerNames;

        public ReservationDetails(LocalDate departureDate, LocalDate returnDate, String departureAirport,
                                  String arrivalAirport, String selectedFlight, int numOfPeople, String passengerNames) {
            this.departureDate = departureDate;
            this.returnDate = returnDate;
            this.departureAirport = departureAirport;
            this.arrivalAirport = arrivalAirport;
            this.selectedFlight = selectedFlight;
            this.numOfPeople = numOfPeople;
            this.passengerNames = passengerNames;
        }

        public LocalDate getDepartureDate() {
            return departureDate;
        }

        public LocalDate getReturnDate() {
            return returnDate;
        }

        public String getDepartureAirport() {
            return departureAirport;
        }

        public String getArrivalAirport() {
            return arrivalAirport;
        }

        public String getSelectedFlight() {
            return selectedFlight;
        }

        public int getNumOfPeople() {
            return numOfPeople;
        }

        public String getPassengerNames() {
            return passengerNames;
        }
    }

}
