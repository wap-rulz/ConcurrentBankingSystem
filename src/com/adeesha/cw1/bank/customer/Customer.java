package com.adeesha.cw1.bank.customer;

public class Customer {
    private final String customerID;
    private final String firstName;
    private final String lastName;
    private final String contactNo;
    private final String address;
    private final String nicNo;

    public Customer(String customerID, String firstName, String lastName, String contactNo, String address, String nicNo) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNo = contactNo;
        this.address = address;
        this.nicNo = nicNo;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getAddress() {
        return address;
    }

    public String getNicNo() {
        return nicNo;
    }
}
