package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.entity.Bill;
import com.airtribe.meditrack.entity.TaxedBill;

/**
 * Factory to create bills based on strategy or type.
 */
public class BillFactory {
    public static Bill createBill(String id, Appointment appointment, double baseAmount, BillingStrategy strategy) {
        double total = strategy.computeAmount(baseAmount);
        if (strategy instanceof TaxedBillingStrategy) {
            return new TaxedBill(id, appointment, baseAmount);
        }
        return new Bill(id, appointment, baseAmount);
    }
}
