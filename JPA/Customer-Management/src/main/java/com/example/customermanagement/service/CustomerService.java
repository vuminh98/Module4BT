package com.example.customermanagement.service;

import com.example.customermanagement.exception.DuplicateLastNameException;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.model.Province;
import com.example.customermanagement.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) throws Exception {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new Exception("customer not found!");
        }
        return customerOptional;
    }

    @Override
    public void save(Customer customer) throws DuplicateLastNameException {
        try {
            customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateLastNameException();
        }
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Customer> findAllByFirstNameContaining(String firstname, Pageable pageable) {
        return customerRepository.findAllByFirstNameContaining(firstname, pageable);
    }
}
