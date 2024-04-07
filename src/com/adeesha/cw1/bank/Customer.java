package com.adeesha.cw1.bank;

public class Customer {
    private String customerID;
    private String firstName;
    private String lastName;
    private String contactNo;
    private String address;
    private String nicNo;

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
