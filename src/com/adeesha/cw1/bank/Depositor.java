package com.adeesha.cw1.bank;

import java.math.BigDecimal;

public class Depositor implements Runnable {
    private final BankAccount bankAccount;
    private final BigDecimal amount;

    public Depositor(BankAccount bankAccount, double amount) {
        this.bankAccount = bankAccount;
        this.amount = BigDecimal.valueOf(amount);
    }

    @Override
    public void run() {
        bankAccount.deposit(amount);
    }
}
