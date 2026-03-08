package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.exception.InvalidDataException;

/**
 * Represents a patient in the system.
 */
public class Patient extends Person implements Cloneable {
    private String symptoms;

    public Patient(String id, String name, int age, String phone, String symptoms) {
        super(id, name, age, phone);
        setSymptoms(symptoms);
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        if (symptoms == null) {
            throw new InvalidDataException("Symptoms must not be null");
        }
        this.symptoms = symptoms.trim();
    }

    @Override
    public Patient clone() {
        try {
            Patient cloned = (Patient) super.clone();
            // Strings are immutable; no deep copy required.
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException("Clone not supported", e);
        }
    }

    @Override
    public String toString() {
        return String.format("Patient %s, symptoms='%s'", super.toString(), symptoms);
    }
}
