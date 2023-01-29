package ru.students.airline.exceptions;

public class FlightParseException extends RuntimeException{
    public FlightParseException(String message) {
        super(message);
    }
}
