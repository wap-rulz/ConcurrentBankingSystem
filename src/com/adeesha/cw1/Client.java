package com.adeesha.cw1;

import com.adeesha.cw1.bank.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // Create Bank
        List<BankAccount> bankAccountList = new ArrayList<>();
        Bank bank = new Bank(bankAccountList);

        // Create customers
        Customer customer1 = new Customer("1", "Peter", "Parker", "0777123123", "Colombo", "00001");
        Customer customer2 = new Customer("2", "Tony", "Stark", "0717123456", "Gampaha", "00002");
        Customer customer3 = new Customer("3", "Stephen", "Strange", "0755123555", "Kandy", "00003");
        Customer customer4 = new Customer("4", "Steve", "Rogers", "0727123188", "Galle", "00004");
        Customer customer5 = new Customer("5", "Wade", "Wilson", "0787883188", "Jaffna", "00005");

        // Create Bank Accounts
        List<Customer> customerList1 = new ArrayList<>();
        customerList1.add(customer1);
        BankAccount bankAccount1 = bank.createAccount("20240001", customerList1, AccountType.REGULAR, false, 0);

        List<Customer> customerList2 = new ArrayList<>();
        Collections.addAll(customerList2, customer2, customer3);
        BankAccount bankAccount2 = bank.createAccount("20240002", customerList2, AccountType.REGULAR, true, 10000);

        List<Customer> customerList3 = new ArrayList<>();
        Collections.addAll(customerList3, customer4, customer5);
        BankAccount bankAccount3 = bank.createAccount("20240003", customerList3, AccountType.VIP, true, 50000);

        Collections.addAll(bankAccountList, bankAccount1, bankAccount2, bankAccount3);

        //Create Thread Groups
        ThreadGroup bankManager = new ThreadGroup("BankManager");
        bankManager.setMaxPriority(8);

        ThreadGroup customerVIP = new ThreadGroup("VIP Customer");
        customerVIP.setMaxPriority(10);

        ThreadGroup customerRegular = new ThreadGroup("Regular Customer");
        customerRegular.setMaxPriority(5);

        // Create Threads
        List<Thread> threadList = new ArrayList<>();

        Thread annualFeeCalculationManager = new Thread(bankManager, new AnnualFeeCalculationManager(bank), "AnnualFeeCalculationManager");
        Thread interestCalculationManager = new Thread(bankManager, new InterestCalculationManager(bank), "InterestCalculationManager");
        Thread taxCalculationManager = new Thread(bankManager, new TaxCalculationManager(bank), "TaxCalculationManager");

        Thread depositorThread1 = new Thread(customerRegular, new Depositor(bankAccount1, 1000), "Depositor 1");
        Thread depositorThread2 = new Thread(customerRegular, new Depositor(bankAccount1, 2000), "Depositor 2");
        Thread depositorThread3 = new Thread(customerRegular, new Depositor(bankAccount1, 3000), "Depositor 3");
        Thread depositorThread4 = new Thread(customerRegular, new Depositor(bankAccount1, 4000), "Depositor 4");
        Thread depositorThread5 = new Thread(customerRegular, new Depositor(bankAccount1, 5000), "Depositor 5");

        Thread withdrawerThread1 = new Thread(customerRegular, new Withdrawer(customer1, bankAccount1, 1000), "Withdrawer 1");
        Thread withdrawerThread2 = new Thread(customerRegular, new Withdrawer(customer1, bankAccount1, 2000), "Withdrawer 2");
        Thread withdrawerThread3 = new Thread(customerRegular, new Withdrawer(customer1, bankAccount1, 3000), "Withdrawer 3");
        Thread withdrawerThread4 = new Thread(customerRegular, new Withdrawer(customer1, bankAccount1, 4000), "Withdrawer 4");
        Thread withdrawerThread5 = new Thread(customerRegular, new Withdrawer(customer1, bankAccount1, 5000), "Withdrawer 5");

        Thread depositorThread6 = new Thread(customerRegular, new Depositor(bankAccount2, 1000), "Depositor 6");
        Thread depositorThread7 = new Thread(customerRegular, new Depositor(bankAccount2, 2000), "Depositor 7");
        Thread depositorThread8 = new Thread(customerRegular, new Depositor(bankAccount2, 3000), "Depositor 8");
        Thread depositorThread9 = new Thread(customerRegular, new Depositor(bankAccount2, 4000), "Depositor 9");
        Thread depositorThread10 = new Thread(customerRegular, new Depositor(bankAccount2, 5000), "Depositor 10");

        Thread withdrawerThread6 = new Thread(customerRegular, new Withdrawer(customer2, bankAccount2, 5000), "Withdrawer 6");
        Thread withdrawerThread7 = new Thread(customerRegular, new Withdrawer(customer2, bankAccount2, 6000), "Withdrawer 7");
        Thread withdrawerThread8 = new Thread(customerRegular, new Withdrawer(customer3, bankAccount2, 7000), "Withdrawer 8");
        Thread withdrawerThread9 = new Thread(customerRegular, new Withdrawer(customer3, bankAccount2, 8000), "Withdrawer 9");
        Thread withdrawerThread10 = new Thread(customerRegular, new Withdrawer(customer3, bankAccount2, 9000), "Withdrawer 10");

        Thread depositorThread11 = new Thread(customerVIP, new Depositor(bankAccount3, 1000), "Depositor 11");
        Thread depositorThread12 = new Thread(customerVIP, new Depositor(bankAccount3, 2000), "Depositor 12");
        Thread depositorThread13 = new Thread(customerVIP, new Depositor(bankAccount3, 3000), "Depositor 13");
        Thread depositorThread14 = new Thread(customerVIP, new Depositor(bankAccount3, 4000), "Depositor 14");
        Thread depositorThread15 = new Thread(customerVIP, new Depositor(bankAccount3, 5000), "Depositor 15");

        Thread withdrawerThread11 = new Thread(customerVIP, new Withdrawer(customer4, bankAccount3, 5000), "Withdrawer 11");
        Thread withdrawerThread12 = new Thread(customerVIP, new Withdrawer(customer4, bankAccount3, 6000), "Withdrawer 12");
        Thread withdrawerThread13 = new Thread(customerVIP, new Withdrawer(customer4, bankAccount3, 7000), "Withdrawer 13");
        Thread withdrawerThread14 = new Thread(customerVIP, new Withdrawer(customer5, bankAccount3, 8000), "Withdrawer 14");
        Thread withdrawerThread15 = new Thread(customerVIP, new Withdrawer(customer5, bankAccount3, 9000), "Withdrawer 15");

        Collections.addAll(threadList, depositorThread1, depositorThread2, depositorThread3, depositorThread4, depositorThread5, depositorThread6, depositorThread7,
                depositorThread8, depositorThread9, depositorThread10, depositorThread11, depositorThread12, depositorThread13, depositorThread14, depositorThread15,
                withdrawerThread1, withdrawerThread2, withdrawerThread3, withdrawerThread4, withdrawerThread5, withdrawerThread6, withdrawerThread7, withdrawerThread8,
                withdrawerThread9, withdrawerThread10, withdrawerThread11, withdrawerThread12, withdrawerThread13, withdrawerThread14, withdrawerThread15,
                annualFeeCalculationManager, interestCalculationManager, taxCalculationManager);

        for (Thread thread : threadList) {
            thread.start();
        }
    }
}
