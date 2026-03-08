package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;

/**
 * Represents a bill issued for an appointment.
 */
public class Bill {
    private final String id;
    private final Appointment appointment;
    private final double amount;

    public Bill(String id, Appointment appointment, double amount) {
        this.id = id;
        this.appointment = appointment;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Base bill generation: just base amount.
     */
    public double generateBill() {
        return amount;
    }

    public BillSummary summarize() {
        double total = generateBill();
        double tax = total * Constants.TAX_RATE;
        double net = total + tax;
        return new BillSummary(id, appointment.getId(), total, tax, net);
    }

    @Override
    public String toString() {
        return String.format("Bill[id=%s, appointment=%s, amount=%.2f]", id, appointment.getId(), amount);
    }
}
