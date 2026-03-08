package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

/**
 * Base class for people in the system.
 */
public class Person extends MedicalEntity {
    private String name;
    private int age;
    private String phone;

    public Person(String id, String name, int age, String phone) {
        super(id);
        setName(name);
        setAge(age);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.validateNonEmpty(name, "name");
        this.name = name.trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        Validator.validatePositive(age, "age");
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        Validator.validateNonEmpty(phone, "phone");
        this.phone = phone.trim();
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s (id=%s, age=%d, phone=%s)", name, getId(), age, phone);
    }
}
