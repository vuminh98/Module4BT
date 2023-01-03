package com.example.customermanagement.service;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public interface ICustomerService extends IGeneralService<Customer>{
    Iterable<Customer> findAllByProvince(Province province);

    Page<Customer> findAll(Pageable pageable);


    Page<Customer> findAll(Sort sort);

    Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable);
}
