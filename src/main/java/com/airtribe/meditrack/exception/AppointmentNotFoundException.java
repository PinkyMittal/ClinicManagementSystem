package com.airtribe.meditrack.exception;

/**
 * Thrown when an appointment cannot be found.
 */
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
