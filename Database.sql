CREATE DATABASE airline_reservation;

USE airline_reservation;

CREATE TABLE Passenger (
    TicketNumber INT PRIMARY KEY,
    DepartureDate DATE,
    ReturnDate DATE,
    DepartureAirport VARCHAR(255),
    ArrivalAirport VARCHAR(255),
    SelectedFlight VARCHAR(255),
    NumberOfPeople INT,
    PassengerNames VARCHAR(255),
    ContactInfo VARCHAR(255),
    Email VARCHAR(255),
    Price DOUBLE
);

    