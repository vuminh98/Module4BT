package com.example.demo1.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    private Long id;
    private String name;
    private String address;

    public Customer() {
    }
}
