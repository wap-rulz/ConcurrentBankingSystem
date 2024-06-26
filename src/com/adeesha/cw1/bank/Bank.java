package com.adeesha.cw1.bank;

import com.adeesha.cw1.bank.account.AccountType;
import com.adeesha.cw1.bank.account.BankAccount;
import com.adeesha.cw1.bank.util.Constants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Bank {
    private final List<BankAccount> bankAccountList;

    public Bank(List<BankAccount> accountList) {
        this.bankAccountList = accountList;
    }

    public BankAccount createAccount(String accountNumber, AccountType accountType, boolean isOverDrawAvailable, double overdraftLimit) {
        BankAccount bankAccount = new BankAccount(accountNumber, accountType, isOverDrawAvailable, overdraftLimit);
        bankAccountList.add(bankAccount);
        return bankAccount;
    }

    public boolean removeAccount(String accountNumber) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getAccountNumber().equals(accountNumber)) {
                bankAccountList.remove(bankAccount);
                return true;
            }
        }
        return false;
    }

    public void addInterest() {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal interest = calculateInterest(bankAccount);
                bankAccount.addInterest(interest);
            }
        }
    }

    private BigDecimal calculateInterest(BankAccount bankAccount) {
        if (bankAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            // interest = (bankAccount.getBalance() * bankAccount.getAccountType().getInterestRate()) / 100) / 12
            return ((bankAccount.getBalance().multiply(BigDecimal.valueOf(bankAccount.getAccountType().getInterestRate())))
                    .divide(BigDecimal.valueOf(100), Constants.DEFAULT_SCALE, RoundingMode.HALF_UP)).divide(BigDecimal.valueOf(12), Constants.DEFAULT_SCALE, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public void deductTax() {
        for (BankAccount bankAccount : bankAccountList) {
            // tax = (calculateInterest(bankAccount) * INCOME_TAX_RATE) / 100;
            BigDecimal tax = (calculateInterest(bankAccount).multiply(Constants.INCOME_TAX_RATE)).divide(BigDecimal.valueOf(100), Constants.DEFAULT_SCALE, RoundingMode.HALF_UP);
            if (tax.compareTo(BigDecimal.ZERO) > 0) {
                bankAccount.deductTax(tax);
            }
        }
    }

    public void deductAnnualFee() {
        for (BankAccount bankAccount : bankAccountList) {
            bankAccount.deductAnnualFee(BigDecimal.valueOf(bankAccount.getAccountType().getAnnualFee()));
        }
    }
}
