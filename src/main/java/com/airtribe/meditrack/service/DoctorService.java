package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.interfaces.Searchable;
import com.airtribe.meditrack.util.DataStore;
import com.airtribe.meditrack.util.IdGenerator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service to manage doctors.
 */
public class DoctorService implements Searchable<Doctor> {
    private static int instanceCounter;
    private final DataStore<Doctor> store = new DataStore<>();

    static {
        // static block for class-level initialization
        instanceCounter = 0;
    }

    public DoctorService() {
        instanceCounter++;
    }

    public Doctor createDoctor(String name, int age, String phone, Specialization specialization, double fee) {
        String id = IdGenerator.getInstance().nextId("D-");
        Doctor doctor = new Doctor(id, name, age, phone, specialization, fee);
        store.add(id, doctor);
        return doctor;
    }

    public Doctor getById(String id) {
        return store.get(id);
    }

    public boolean deleteById(String id) {
        return store.remove(id);
    }

    public List<Doctor> listAll() {
        return store.list();
    }

    public int getDoctorCount() {
        return store.size();
    }

    public static int getInstanceCounter() {
        return instanceCounter;
    }

    @Override
    public List<Doctor> searchById(String id) {
        Doctor found = store.get(id);
        return found != null ? List.of(found) : List.of();
    }

    @Override
    public List<Doctor> searchByName(String name) {
        return store.list().stream()
                .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> searchByAge(int age) {
        return store.list().stream()
                .filter(d -> d.getAge() == age)
                .collect(Collectors.toList());
    }
}
