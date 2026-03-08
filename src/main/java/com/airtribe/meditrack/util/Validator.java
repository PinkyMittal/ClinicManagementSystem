package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;

/**
 * Common validation methods.
 */
public class Validator {
    public static void validateNonEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidDataException(fieldName + " must not be empty");
        }
    }

    public static void validatePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + " must be positive");
        }
    }

    public static void validatePositive(double value, String fieldName) {
        if (value < 0) {
            throw new InvalidDataException(fieldName + " must be non-negative");
        }
    }
}
