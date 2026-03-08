package com.airtribe.meditrack.service;

/**
 * Billing strategy that returns the base amount.
 */
public class NormalBillingStrategy implements BillingStrategy {

    @Override
    public double computeAmount(double baseAmount) {
        return baseAmount;
    }
}
