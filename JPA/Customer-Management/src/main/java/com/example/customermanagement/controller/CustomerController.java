package com.example.customermanagement.controller;

import com.example.customermanagement.exception.DuplicateLastNameException;
import com.example.customermanagement.model.Customer;
import com.example.customermanagement.model.Province;
import com.example.customermanagement.service.ICustomerService;
import com.example.customermanagement.service.IProvinceService;
import com.example.customermanagement.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

    ProvinceService provinceService = new ProvinceService();

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.findAll();
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        try {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("/customer/create");
            modelAndView.addObject("customer", new Customer());
            modelAndView.addObject("message", "New customer created successfully");
            return modelAndView;
        } catch (DuplicateLastNameException e) {
            return new ModelAndView("/customer/inputs-not-acceptable");
        }

    }


    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) throws Exception {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            return new ModelAndView("/customer/error.404");
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customers";
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws Exception {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            return new ModelAndView("/customer/error.404");
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        try {
            customerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("message", "Customer updated successfully");
            return modelAndView;
        } catch (DuplicateLastNameException e) {
            return new ModelAndView("/customer/inputs-not-acceptable");
        }


    }

    @GetMapping("/customers")
    public ModelAndView listCustomers(@RequestParam("search") Optional<String> search, @PageableDefault(value = 5) Pageable pageable) throws Exception {
        Page<Customer> customers = customerService.findAll(pageable);
        if (search.isPresent()) {
            customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    public ModelAndView showInformation(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            Optional<Customer> customerOptional = customerService.findById(id);
            modelAndView.addObject("customer", customerOptional.get());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/customers");
        }
    }

    @ExceptionHandler(DuplicateLastNameException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/customer/inputs-not-acceptable");
    }
}
