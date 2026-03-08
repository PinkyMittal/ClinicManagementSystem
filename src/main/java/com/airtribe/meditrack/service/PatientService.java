package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage patients.
 */
public class PatientService implements Searchable<Patient> {
    private final DataStore<Patient> store = new DataStore<>();

    public Patient createPatient(String name, int age, String phone, String symptoms) {
        String id = IdGenerator.getInstance().nextId("P-");
        Patient patient = new Patient(id, name, age, phone, symptoms);
        store.add(id, patient);
        return patient;
    }

    public Patient getById(String id) {
        return store.get(id);
    }

    public boolean deleteById(String id) {
        return store.remove(id);
    }

    public List<Patient> listAll() {
        return store.list();
    }

    /**
     * Overloaded search methods demonstrate polymorphism via method overloading.
     */
    public List<Patient> searchPatient(String id) {
        return searchById(id);
    }

    public List<Patient> searchPatientByName(String name) {
        return searchByName(name);
    }

    public List<Patient> searchPatientByAge(int age) {
        return searchByAge(age);
    }

    @Override
    public List<Patient> searchById(String id) {
        Patient found = store.get(id);
        return found != null ? List.of(found) : List.of();
    }

    @Override
    public List<Patient> searchByName(String name) {
        return store.list().stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> searchByAge(int age) {
        return store.list().stream()
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toList());
    }
}
