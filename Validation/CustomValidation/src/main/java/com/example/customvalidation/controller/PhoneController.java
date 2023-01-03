package com.example.customvalidation.controller;

import com.example.customvalidation.model.PhoneNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PhoneController {

    @GetMapping("/")
    public ModelAndView showForm() {
        return new ModelAndView("/index","phoneNumber", new PhoneNumber());
    }

    @PostMapping("/")
    public String checkValidation(@Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, BindingResult bindingResult, Model model) {
        new PhoneNumber().validate(phoneNumber, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "/index";
        }
        model.addAttribute("phoneNumber", phoneNumber);
        return "/result";
    }
}
