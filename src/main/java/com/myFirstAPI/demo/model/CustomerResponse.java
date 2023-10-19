package com.myFirstAPI.demo.model;

import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomerResponse {

    private String message;
    private CustomerData customerData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }





}
