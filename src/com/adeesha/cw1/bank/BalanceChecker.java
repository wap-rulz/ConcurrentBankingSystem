package com.adeesha.cw1.bank;

import java.text.DecimalFormat;

import static com.adeesha.cw1.bank.Constants.DECIMAL_FORMAT_PATTERN;

public class BalanceChecker implements Runnable {
    private final BankAccount bankAccount;
    private final Customer customer;
    DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);

    public BalanceChecker(BankAccount bankAccount, Customer customer) {
        this.bankAccount = bankAccount;
        this.customer = customer;
    }


    @Override
    public void run() {

    }
}
