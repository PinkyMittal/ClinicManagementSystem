package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.util.DateUtil;

import java.time.LocalDateTime;

/**
 * Represents a scheduled appointment between a doctor and a patient.
 */
public class Appointment implements Cloneable {
    private final String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private AppointmentStatus status;

    public Appointment(String id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.id = id;
        setPatient(patient);
        setDoctor(doctor);
        setDateTime(dateTime);
        this.status = AppointmentStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient must not be null");
        }
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor must not be null");
        }
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Date/time must not be null");
        }
        this.dateTime = dateTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status must not be null");
        }
        this.status = status;
    }

    public void cancel() {
        setStatus(AppointmentStatus.CANCELLED);
    }

    public String getFormattedDateTime() {
        return DateUtil.format(dateTime);
    }

    @Override
    public Appointment clone() {
        try {
            Appointment cloned = (Appointment) super.clone();
            cloned.patient = this.patient.clone();
            cloned.doctor = new Doctor(doctor.getId(), doctor.getName(), doctor.getAge(), doctor.getPhone(), doctor.getSpecialization(), doctor.getConsultationFee());
            cloned.dateTime = LocalDateTime.from(dateTime);
            cloned.status = status;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Appointment[id=%s, doctor=%s, patient=%s, at=%s, status=%s]", id, doctor.getName(), patient.getName(), getFormattedDateTime(), status);
    }
}
