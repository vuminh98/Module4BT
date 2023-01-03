package com.example.demostoreprocedure.repository;

import com.example.demostoreprocedure.model.Customer;

public interface ICustomerRepository {
    boolean insertWithStoredProcedure(Customer customer);
}
