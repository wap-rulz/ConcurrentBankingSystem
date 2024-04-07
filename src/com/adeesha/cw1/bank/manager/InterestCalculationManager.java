package com.adeesha.cw1.bank.manager;

import com.adeesha.cw1.bank.Bank;

public class InterestCalculationManager implements Runnable {
    private final Bank bank;

    public InterestCalculationManager(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        bank.addInterest();
    }
}
