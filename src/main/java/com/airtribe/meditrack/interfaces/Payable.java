package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.Bill;

/**
 * Entities that can generate a bill.
 */
public interface Payable {
    Bill generateBill();

    default void printBill() {
        Bill bill = generateBill();
        System.out.println("--- Bill ---");
        System.out.println(bill);
        System.out.println(bill.summarize());
    }
}
