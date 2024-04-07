package com.adeesha.cw1.bank;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Withdrawer implements Runnable {
    private final Customer withdrawCustomer;
    private final BankAccount bankAccount;
    private final BigDecimal amount;
    private final DecimalFormat df;

    public Withdrawer(Customer customer, BankAccount bankAccount, double amount) {
        this.withdrawCustomer = customer;
        this.bankAccount = bankAccount;
        this.amount = BigDecimal.valueOf(amount);
        this.df = new DecimalFormat("#0.00");
    }

    @Override
    public void run() {
        for (Customer customer : bankAccount.getCustomerList()) {
            if (customer.equals(withdrawCustomer)) {
                try {
                    bankAccount.withdraw(amount);
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() +  ": Error in withdrawing from account: " + bankAccount.getAccountNumber() + ", Amount to withdraw: " + df.format(amount) + ", Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Withdrawal failed. Customer " + withdrawCustomer.getCustomerID() + " is not an owner of account: " + bankAccount.getAccountNumber());
    }
}