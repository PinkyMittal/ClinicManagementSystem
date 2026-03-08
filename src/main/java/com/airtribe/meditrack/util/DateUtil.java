package com.airtribe.meditrack.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility methods for working with dates and times.
 */
public class DateUtil {
    public static final DateTimeFormatter DEFAULT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime parse(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Date/time text must not be empty");
        }
        try {
            return LocalDateTime.parse(text.trim(), DEFAULT_FORMAT);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid date/time. Use format: " + DEFAULT_FORMAT, ex);
        }
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DEFAULT_FORMAT);
    }
}
