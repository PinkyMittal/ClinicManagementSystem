package com.airtribe.meditrack.exception;

/**
 * Thrown when provided data is invalid.
 */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
