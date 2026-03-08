package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.AIHelper;
import com.airtribe.meditrack.util.DateUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Console application entry point.
 */
public class Main {
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.run(args);
    }

    private void run(String[] args) {
        if (args != null) {
            for (String arg : args) {
                if ("--loadData".equals(arg)) {
                    loadPersistedData();
                }
            }
        }

        showMenuLoop();
    }

    private void loadPersistedData() {
        System.out.println("Loading persisted data (if present)...");
        try {
            // Load doctors and patients first
            // These methods are intentionally simple; they will not overwrite existing objects.
            DataPersistence.loadDoctors(doctorService);
            DataPersistence.loadPatients(patientService);
            appointmentService.loadFromCsv(patientService, doctorService);
            System.out.println("Data loaded.");
        } catch (IOException e) {
            System.err.println("Warning: could not load persisted data: " + e.getMessage());
        }
    }

    private void showMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MediTrack Menu ===");
            System.out.println("1) Add doctor");
            System.out.println("2) Add patient");
            System.out.println("3) Create appointment");
            System.out.println("4) List doctors");
            System.out.println("5) List patients");
            System.out.println("6) List appointments");
            System.out.println("7) Cancel appointment");
            System.out.println("8) Generate bill");
            System.out.println("9) Save data");
            System.out.println("0) Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addDoctor();
                case "2" -> addPatient();
                case "3" -> createAppointment();
                case "4" -> listDoctors();
                case "5" -> listPatients();
                case "6" -> listAppointments();
                case "7" -> cancelAppointment();
                case "8" -> generateBill();
                case "9" -> saveData();
                case "0" -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Unknown option. Try again.");
            }
        }
    }

    private void addDoctor() {
        System.out.println("--- Add Doctor ---");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();

        System.out.println("Choose specialization:");
        for (Specialization s : Specialization.values()) {
            System.out.printf("%d) %s\n", s.ordinal() + 1, s);
        }
        System.out.print("Specialization #: ");
        int specChoice = Integer.parseInt(scanner.nextLine().trim());
        Specialization specialization = Specialization.values()[Math.max(0, specChoice - 1)];

        System.out.print("Consultation fee: ");
        double fee = Double.parseDouble(scanner.nextLine().trim());

        Doctor doctor = doctorService.createDoctor(name, age, phone, specialization, fee);
        System.out.println("Created: " + doctor);
    }

    private void addPatient() {
        System.out.println("--- Add Patient ---");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Symptoms: ");
        String symptoms = scanner.nextLine().trim();

        Patient patient = patientService.createPatient(name, age, phone, symptoms);
        System.out.println("Created: " + patient);

        Specialization recommended = AIHelper.recommendSpecialization(symptoms);
        System.out.println("Recommended specialization: " + recommended);
    }

    private void createAppointment() {
        System.out.println("--- Create Appointment ---");
        System.out.print("Patient ID: ");
        String patientId = scanner.nextLine().trim();
        Patient patient = patientService.getById(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Doctor ID: ");
        String doctorId = scanner.nextLine().trim();
        Doctor doctor = doctorService.getById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Date/time (yyyy-MM-dd HH:mm): ");
        LocalDateTime dateTime;
        try {
            dateTime = DateUtil.parse(scanner.nextLine().trim());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid date/time: " + ex.getMessage());
            return;
        }

        Appointment appointment = appointmentService.createAppointment(patient, doctor, dateTime);
        System.out.println("Created: " + appointment);
    }

    private void listDoctors() {
        System.out.println("--- Doctors ---");
        doctorService.listAll().forEach(System.out::println);
    }

    private void listPatients() {
        System.out.println("--- Patients ---");
        patientService.listAll().forEach(System.out::println);
    }

    private void listAppointments() {
        System.out.println("--- Appointments ---");
        appointmentService.listAll().forEach(System.out::println);
    }

    private void cancelAppointment() {
        System.out.print("Appointment ID to cancel: ");
        String id = scanner.nextLine().trim();
        try {
            appointmentService.cancelAppointment(id);
            System.out.println("Appointment cancelled.");
        } catch (Exception e) {
            System.out.println("Could not cancel appointment: " + e.getMessage());
        }
    }

    private void generateBill() {
        System.out.print("Appointment ID to bill: ");
        String id = scanner.nextLine().trim();
        try {
            Bill bill = appointmentService.generateBill(id);
            System.out.println(bill);
            System.out.println(bill.summarize());
        } catch (Exception e) {
            System.out.println("Could not generate bill: " + e.getMessage());
        }
    }

    private void saveData() {
        System.out.println("Saving data to disk...");
        try {
            DataPersistence.saveDoctors(doctorService);
            DataPersistence.savePatients(patientService);
            appointmentService.saveToCsv();
            System.out.println("Saved.");
        } catch (IOException e) {
            System.err.println("Failed to save data: " + e.getMessage());
        }
    }
}
