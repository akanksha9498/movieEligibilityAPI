package com.myFirstAPI.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomerData {
    private String name;
    @JsonIgnore
    private int age;
    private boolean isEligible;

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
