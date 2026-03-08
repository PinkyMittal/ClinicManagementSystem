package com.airtribe.meditrack.test;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.service.AppointmentService;
import com.airtribe.meditrack.service.DoctorService;
import com.airtribe.meditrack.service.PatientService;
import com.airtribe.meditrack.util.DateUtil;

import java.time.LocalDateTime;

/**
 * Manual test runner to validate core functionality.
 */
public class TestRunner {
    public static void main(String[] args) {
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        var doctor = doctorService.createDoctor("Alice", 40, "555-1234", com.airtribe.meditrack.enums.Specialization.CARDIOLOGY, 200);
        var patient = patientService.createPatient("Bob", 30, "555-5678", "chest pain");

        System.out.println("Doctor: " + doctor);
        System.out.println("Patient: " + patient);

        LocalDateTime when = DateUtil.parse("2026-04-01 09:30");
        Appointment appointment = appointmentService.createAppointment(patient, doctor, when);

        System.out.println("Appointment created: " + appointment);

        Appointment cloned = appointment.clone();
        System.out.println("Cloned appointment: " + cloned);

        patient.setSymptoms("headache");
        System.out.println("Original patient symptoms after change: " + appointment.getPatient().getSymptoms());
        System.out.println("Cloned patient symptoms after change: " + cloned.getPatient().getSymptoms());

        var bill = appointmentService.generateBill(appointment.getId());
        System.out.println("Generated bill: " + bill);
        System.out.println("Bill summary: " + bill.summarize());
    }
}
