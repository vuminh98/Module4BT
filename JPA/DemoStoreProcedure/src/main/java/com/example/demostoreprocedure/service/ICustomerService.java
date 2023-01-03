package com.example.demostoreprocedure.service;

import com.example.demostoreprocedure.model.Customer;

public interface ICustomerService {
    boolean insertWithStoredProcedure(Customer customer);
}
