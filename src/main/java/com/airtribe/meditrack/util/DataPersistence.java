package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper for saving/loading data from CSV files.
 */
public class DataPersistence {

    public static void saveDoctors(DoctorService service) throws IOException {
        File parent = new File(Constants.DOCTORS_CSV).getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        List<String[]> rows = new ArrayList<>();
        for (Doctor d : service.listAll()) {
            rows.add(new String[]{
                    d.getId(),
                    d.getName(),
                    String.valueOf(d.getAge()),
                    d.getPhone(),
                    d.getSpecialization().name(),
                    String.valueOf(d.getConsultationFee())
            });
        }
        CSVUtil.writeCsv(Constants.DOCTORS_CSV, rows);
    }

    public static void loadDoctors(DoctorService service) throws IOException {
        File file = new File(Constants.DOCTORS_CSV);
        if (!file.exists()) {
            return;
        }
        List<String[]> rows = CSVUtil.readCsv(file.getAbsolutePath());
        for (String[] row : rows) {
            if (row.length < 6) {
                continue;
            }
            try {
                service.createDoctor(row[1], Integer.parseInt(row[2]), row[3], com.airtribe.meditrack.enums.Specialization.valueOf(row[4]), Double.parseDouble(row[5]));
            } catch (Exception ignored) {
                // ignore malformed rows
            }
        }
    }

    public static void savePatients(PatientService service) throws IOException {
        File parent = new File(Constants.PATIENTS_CSV).getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        List<String[]> rows = new ArrayList<>();
        for (Patient p : service.listAll()) {
            rows.add(new String[]{
                    p.getId(),
                    p.getName(),
                    String.valueOf(p.getAge()),
                    p.getPhone(),
                    p.getSymptoms()
            });
        }
        CSVUtil.writeCsv(Constants.PATIENTS_CSV, rows);
    }

    public static void loadPatients(PatientService service) throws IOException {
        File file = new File(Constants.PATIENTS_CSV);
        if (!file.exists()) {
            return;
        }
        List<String[]> rows = CSVUtil.readCsv(file.getAbsolutePath());
        for (String[] row : rows) {
            if (row.length < 5) {
                continue;
            }
            try {
                service.createPatient(row[1], Integer.parseInt(row[2]), row[3], row[4]);
            } catch (Exception ignored) {
                // ignore malformed rows
            }
        }
    }
}
