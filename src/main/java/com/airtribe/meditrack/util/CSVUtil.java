package com.airtribe.meditrack.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Minimal CSV utilities (no external dependencies) for persistence.
 */
public class CSVUtil {
    public static List<String[]> readCsv(String path) throws IOException {
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                rows.add(line.split(","));
            }
        }
        return rows;
    }

    public static void writeCsv(String path, List<String[]> rows) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }
}
