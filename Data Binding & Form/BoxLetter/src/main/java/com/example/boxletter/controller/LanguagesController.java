package com.example.boxletter.controller;

import com.example.boxletter.model.Languages;
import com.example.boxletter.service.LanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class LanguagesController {

    @Autowired
    private LanguagesService languagesService;


   @ModelAttribute("languages")
    public List<Languages> getLanguages() {
       return languagesService.findAll();
   }

   @GetMapping(value = "/view")
    public ModelAndView findAll() {
       return new ModelAndView("view");
   }
}
