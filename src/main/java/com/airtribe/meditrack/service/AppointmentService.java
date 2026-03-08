package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.entity.TaxedBill;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DateUtil;
import com.airtribe.meditrack.util.IdGenerator;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages appointments and related billing.
 */
public class AppointmentService {
    private final DataStore<Appointment> store = new DataStore<>();

    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        String id = IdGenerator.getInstance().nextId("A-");
        Appointment appointment = new Appointment(id, patient, doctor, dateTime);
        store.add(id, appointment);
        return appointment;
    }

    public Appointment getById(String id) {
        Appointment appointment = store.get(id);
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment not found: " + id);
        }
        return appointment;
    }

    public List<Appointment> listAll() {
        return store.list();
    }

    public void cancelAppointment(String id) {
        Appointment appointment = getById(id);
        appointment.cancel();
    }

    public List<Appointment> listByStatus(AppointmentStatus status) {
        return store.list().stream()
                .filter(a -> a.getStatus() == status)
                .collect(Collectors.toList());
    }

    public Bill generateBill(String appointmentId) {
        Appointment appointment = getById(appointmentId);
        double baseAmount = appointment.getDoctor().getConsultationFee();
        Bill bill = BillFactory.createBill(IdGenerator.getInstance().nextId("B-"), appointment, baseAmount, new TaxedBillingStrategy());
        return bill;
    }

    public List<Appointment> searchByDoctorName(String doctorName) {
        return store.list().stream()
                .filter(a -> a.getDoctor().getName().toLowerCase().contains(doctorName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Appointment> searchByPatientName(String patientName) {
        return store.list().stream()
                .filter(a -> a.getPatient().getName().toLowerCase().contains(patientName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Persists appointments to a CSV file.
     */
    public void saveToCsv() throws IOException {
        File parent = new File(Constants.APPOINTMENTS_CSV).getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        List<String[]> rows = new ArrayList<>();
        for (Appointment a : store.list()) {
            rows.add(new String[]{
                    a.getId(),
                    a.getPatient().getId(),
                    a.getDoctor().getId(),
                    DateUtil.format(a.getDateTime()),
                    a.getStatus().name()
            });
        }
        CSVUtil.writeCsv(Constants.APPOINTMENTS_CSV, rows);
    }

    /**
     * Loads appointments from the CSV file. Requires patient and doctor services to resolve references.
     */
    public void loadFromCsv(PatientService patientService, DoctorService doctorService) throws IOException {
        File file = new File(Constants.APPOINTMENTS_CSV);
        if (!file.exists()) {
            return;
        }
        List<String[]> rows = CSVUtil.readCsv(file.getAbsolutePath());
        for (String[] row : rows) {
            if (row.length < 5) {
                continue;
            }
            String id = row[0];
            Patient patient = patientService.getById(row[1]);
            Doctor doctor = doctorService.getById(row[2]);
            LocalDateTime dateTime = DateUtil.parse(row[3]);
            Appointment appointment = new Appointment(id, patient, doctor, dateTime);
            appointment.setStatus(AppointmentStatus.valueOf(row[4]));
            store.add(id, appointment);
        }
    }
}
