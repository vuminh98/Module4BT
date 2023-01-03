package com.example.productspringboot.controller;

import com.example.productspringboot.model.Category;
import com.example.productspringboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }


}
