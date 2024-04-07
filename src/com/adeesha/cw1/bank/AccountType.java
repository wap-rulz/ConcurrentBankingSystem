package com.adeesha.cw1.bank;

public enum AccountType {
    VIP(5, 0),
    REGULAR(3, 500);

    private final double interestRate;
    private final double annualFee;

    private AccountType(double interestRate, double annualFee) {
        this.interestRate = interestRate;
        this.annualFee = annualFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getAnnualFee() {
        return annualFee;
    }
}
