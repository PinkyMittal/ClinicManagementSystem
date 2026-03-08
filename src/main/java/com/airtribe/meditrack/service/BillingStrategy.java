package com.airtribe.meditrack.service;

/**
 * Strategy interface for computing a bill amount.
 */
public interface BillingStrategy {
    double computeAmount(double baseAmount);
}
