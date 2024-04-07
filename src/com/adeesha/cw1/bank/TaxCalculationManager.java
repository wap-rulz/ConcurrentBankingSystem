package com.adeesha.cw1.bank;

public class TaxCalculationManager implements Runnable {
    private final Bank bank;

    public TaxCalculationManager(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.deductTax();
    }
}
