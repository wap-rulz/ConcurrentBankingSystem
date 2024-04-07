package com.adeesha.cw1.bank.account;

import com.adeesha.cw1.bank.customer.Customer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import static com.adeesha.cw1.bank.util.Constants.*;

public class BankAccount {
    private String accountNumber;
    private List<Customer> customerList;
    private BigDecimal balance;
    private AccountType accountType;
    private boolean isOverdraftAvailable;
    private BigDecimal overdraftLimit;
    private final DecimalFormat df;

    public BankAccount(String accountNumber, List<Customer> customerList, AccountType accountType, boolean isOverdraftAvailable, double overdraftLimit) {
        this.balance = BigDecimal.ZERO;
        this.accountNumber = accountNumber;
        this.customerList = customerList;
        this.accountType = accountType;
        this.isOverdraftAvailable = isOverdraftAvailable;
        this.overdraftLimit = BigDecimal.valueOf(overdraftLimit);
        this.df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
    }

    public void addCustomer(Customer customer) {
        this.customerList.add(customer);
    }

    public boolean removeCustomer(String customerID) {
        boolean isFound = false;
        for (Customer customer : customerList) {
            if (customer.getCustomerID().equals(customerID)) {
                customerList.remove(customer);
                return true;
            }
        }
        return false;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public synchronized void withdraw(BigDecimal amount) {
        BigDecimal amountAvailable;
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            if (isOverdraftAvailable) {
                amountAvailable = balance.add(overdraftLimit);
            } else {
                amountAvailable = balance;
            }
            if (amount.compareTo(amountAvailable) <= 0) {
                chargeOverdraftFees(amount);
                balance = balance.subtract(amount);
                System.out.println(Thread.currentThread().getName() + ": withdrawal successful from account: " + accountNumber + ", Amount withdrawn: " + df.format(amount) + ", Balance after withdrawal: " + df.format(balance));
            } else {
                throw new IllegalArgumentException("Insufficient funds. Balance available to withdraw: " + df.format(amountAvailable));
            }
        } else {
            throw new IllegalArgumentException("Amount to withdraw can not be 0 or below.");
        }
    }

    public synchronized void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(amount);
            System.out.println(Thread.currentThread().getName() + ": deposit successful to account: " + accountNumber + ", Amount deposited: " + df.format(amount) + ", Balance after deposit: " + df.format(balance));
        } else {
            throw new IllegalArgumentException("Amount to deposit can not be 0 or below.");
        }
    }

    public synchronized void addInterest(BigDecimal interest) {
        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(interest);
            System.out.println(Thread.currentThread().getName() + ": Interest add successful to account: " + accountNumber + ", Interest added: " + df.format(interest) + ", Balance after interest: " + df.format(balance));
        } else {
            throw new IllegalArgumentException("Interest can not be 0 or below.");
        }
    }

    //Balance is allowed to go minus when charging tax
    public synchronized void deductTax(BigDecimal tax) {
        balance = balance.subtract(tax);
        System.out.println(Thread.currentThread().getName() + ": tax deducted from account: " + accountNumber + ", amount: " + df.format(tax) +", Balance after deduction: " + df.format(balance));
    }

    //Balance is allowed to go minus when charging annual fee
    public synchronized void deductAnnualFee(BigDecimal fee) {
        if (fee.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.subtract(fee);
            System.out.println(Thread.currentThread().getName() + ": annual fee deducted from account: " + accountNumber + ", amount: " + df.format(fee) + ", Balance after deduction: " + df.format(balance));
        }
    }

    public synchronized void chargeOverdraftFees(BigDecimal amount) {
        if (isOverdraftAvailable && (amount.compareTo(balance) > 0)) {
            // overdraftFee = ((amount - balance) * OVERDRAFT_INTEREST_RATE) / 100
            BigDecimal overdraftFee = ((amount.subtract(balance)).multiply(OVERDRAFT_INTEREST_RATE)).divide(BigDecimal.valueOf(100), DEFAULT_SCALE, RoundingMode.HALF_UP);
            balance = balance.subtract(overdraftFee);
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public synchronized boolean isOverdraftAvailable() {
        return isOverdraftAvailable;
    }

    public synchronized void setOverdraftAvailable(boolean overdraftAvailable) {
        isOverdraftAvailable = overdraftAvailable;
    }

    public synchronized BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public synchronized void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
