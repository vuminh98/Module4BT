package com.example.phonemanagement.controller;

import com.example.phonemanagement.model.SmartPhone;
import com.example.phonemanagement.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("/smartphones")
public class SmartPhoneController {

    @Autowired
    private IPhoneService phoneService;

    @GetMapping("/list")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("smartphones", phoneService.findAll());
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<SmartPhone> createSmartPhone(@RequestBody SmartPhone smartPhone) {
        return new ResponseEntity<>(phoneService.save(smartPhone), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<SmartPhone>> allPhones() {
        return new ResponseEntity<>(phoneService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SmartPhone> deleteSmartphone(@PathVariable Long id) {
        Optional<SmartPhone> smartphoneOptional = phoneService.findById(id);
        if (!smartphoneOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        phoneService.remove(id);
        return new ResponseEntity<>(smartphoneOptional.get(), HttpStatus.NO_CONTENT);
    }

}
