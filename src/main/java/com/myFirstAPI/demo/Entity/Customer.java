package com.myFirstAPI.demo.Entity;

import javax.persistence.*;

@Entity
public class Customer {
    @Id   //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremented primary key
    private int customerID;
    private String name;
    private int age;
    @Transient         //it removes the field from the table, this field won't be visible in my customer table
    private boolean isEligible;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }
}
