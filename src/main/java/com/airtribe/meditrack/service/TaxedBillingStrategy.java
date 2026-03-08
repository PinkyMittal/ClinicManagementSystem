package com.airtribe.meditrack.service;

import com.airtribe.meditrack.constants.Constants;

/**
 * Billing strategy that applies tax to the base amount.
 */
public class TaxedBillingStrategy implements BillingStrategy {

    @Override
    public double computeAmount(double baseAmount) {
        return baseAmount + (baseAmount * Constants.TAX_RATE);
    }
}
