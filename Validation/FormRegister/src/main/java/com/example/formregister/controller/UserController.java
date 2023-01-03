package com.example.formregister.controller;

import com.example.formregister.model.User;
import com.example.formregister.repository.IUserRepository;
import com.example.formregister.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("/list", "users", userService.findAll());
    }

    @GetMapping("/add")
    public ModelAndView createForm(RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("/form");
        modelAndView.addObject("user", new User());
        redirectAttributes.addFlashAttribute("message", "Add successfully");
        return modelAndView;
    }


    @PostMapping("/validateUser")
    public ModelAndView checkValidate(@Validated @ModelAttribute("user") User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/form");
        }
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/form");
        modelAndView.addObject("user", new User());
        redirectAttributes.addFlashAttribute("message","Add successfully");
        return modelAndView;
    }
}
