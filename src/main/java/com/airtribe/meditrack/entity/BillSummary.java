package com.airtribe.meditrack.entity;

/**
 * Immutable summary of a bill.
 */
public final class BillSummary {
    private final String billId;
    private final String appointmentId;
    private final double subtotal;
    private final double tax;
    private final double total;

    public BillSummary(String billId, String appointmentId, double subtotal, double tax, double total) {
        this.billId = billId;
        this.appointmentId = appointmentId;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = total;
    }

    public String getBillId() {
        return billId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("BillSummary[billId=%s, appointmentId=%s, subtotal=%.2f, tax=%.2f, total=%.2f]", billId, appointmentId, subtotal, tax, total);
    }
}
