package com.adeesha.cw1.bank;

public class AnnualFeeCalculationManager implements Runnable {
    private final Bank bank;

    public AnnualFeeCalculationManager(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        //exception handling
        bank.deductAnnualFee();
    }
}
