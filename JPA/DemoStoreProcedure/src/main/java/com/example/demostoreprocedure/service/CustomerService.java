package com.example.demostoreprocedure.service;

import com.example.demostoreprocedure.model.Customer;

import com.example.demostoreprocedure.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        return customerRepository.insertWithStoredProcedure(customer);
    }
}
