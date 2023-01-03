package com.example.login.controller;

import com.example.login.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class UserController {

    @GetMapping
    public ModelAndView home() {
        return new ModelAndView("/login","user",new User());
    }

}
