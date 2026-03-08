package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.constants.Constants;

/**
 * A bill that adds tax automatically.
 */
public class TaxedBill extends Bill {
    public TaxedBill(String id, Appointment appointment, double amount) {
        super(id, appointment, amount);
    }

    @Override
    public double generateBill() {
        double base = super.generateBill();
        return base + (base * Constants.TAX_RATE);
    }
}
