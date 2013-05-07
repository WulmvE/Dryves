package models;

import java.io.Serializable;

public class User implements Serializable {

    private long customerNumber;
    private String name;
    private String streetAddress;
    private String city;
    private String streetNumber;
    private String role;

    public User(long customerNumber, String naam, String straatnaam, String stad) {
        if (customerNumber < 1) {
            throw new IllegalArgumentException(
                    "Customer number may not be negative, value = " + customerNumber);
        }
        if (naam == null) {
            throw new NullPointerException("Customer name may not be empty");
        }

        this.setCustomerNumber(customerNumber);
        this.setName(naam);
        this.setStreetAddress(straatnaam);
        this.setCity(stad);
    }

    public User(long customerNumber, String naam, String straatnaam, String stad, String streetNumber) {
        if (customerNumber < 1) {
            throw new IllegalArgumentException(
                    "Customer number may not be negative, value = " + customerNumber);
        }
        if (naam == null) {
            throw new NullPointerException("Customer name may not be empty");
        }

        this.setCustomerNumber(customerNumber);
        this.setName(naam);
        this.setStreetAddress(straatnaam);
        this.setCity(stad);
        this.setStreetNumber(streetNumber);
    }

    public User() {
    }
    
       public User(long customerNumber, String naam, String straatnaam, String stad, String streetNumber , String role) {
        if (customerNumber < 1) {
            throw new IllegalArgumentException(
                    "Customer number may not be negative, value = " + customerNumber);
        }
        if (naam == null) {
            throw new NullPointerException("Customer name may not be empty");
        }

        this.setCustomerNumber(customerNumber);
        this.setName(naam);
        this.setStreetAddress(straatnaam);
        this.setCity(stad);
        this.setStreetNumber(streetNumber);
        this.setRole(role);
    }

    /* Getters en setters voor de verschillende attributen van het Model */
    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long CustomerNumber) {
        this.customerNumber = CustomerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    
}
