package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.Specialization;

/**
 * A medical doctor with a specialization.
 */
public class Doctor extends Person {
    private Specialization specialization;
    private double consultationFee;

    public Doctor(String id, String name, int age, String phone, Specialization specialization, double consultationFee) {
        super(id, name, age, phone);
        setSpecialization(specialization);
        setConsultationFee(consultationFee);
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        if (specialization == null) {
            throw new IllegalArgumentException("Specialization must not be null");
        }
        this.specialization = specialization;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        if (consultationFee < 0) {
            throw new IllegalArgumentException("Consultation fee cannot be negative");
        }
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return String.format("Dr. %s [%s] Fee: $%.2f", getName(), specialization, consultationFee);
    }
}
