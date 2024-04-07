package com.adeesha.cw1.bank.tranaction;

import com.adeesha.cw1.bank.account.BankAccount;
import com.adeesha.cw1.bank.customer.Customer;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static com.adeesha.cw1.bank.util.Constants.DECIMAL_FORMAT_PATTERN;

public class BalanceChecker implements Runnable {
    private final BankAccount bankAccount;
    private final Customer balanceCheckCustomer;
    private final DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);

    public BalanceChecker(Customer customer, BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.balanceCheckCustomer = customer;
    }


    @Override
    public void run() {
        for (Customer customer : bankAccount.getCustomerList()) {
            if (customer.equals(balanceCheckCustomer)) {
                try {
                    BigDecimal balance = bankAccount.getBalance();
                    System.out.println(Thread.currentThread().getName() + ": balance checking successful. Account: " + bankAccount.getAccountNumber() + ",  Balance: " + df.format(balance) + ", Customer: " + customer.getCustomerID());
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + ": error in checking balance of account: " + bankAccount.getAccountNumber() + ", Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + ": balance check failed. Customer " + balanceCheckCustomer.getCustomerID() + " is not an owner of account: " + bankAccount.getAccountNumber());
    }
}
