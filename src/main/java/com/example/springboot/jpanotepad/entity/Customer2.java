package com.example.springboot.jpanotepad.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Customer2 extends Customer {

    public Customer2(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                getId(), getFirstName(), getLastName());
    }

}
