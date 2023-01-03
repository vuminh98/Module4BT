package com.example.productspringboot.controller;

import com.example.productspringboot.model.Category;
import com.example.productspringboot.model.Product;
import com.example.productspringboot.service.ICategoryService;
import com.example.productspringboot.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,
                                           @PathVariable Long id) {
        Product productUpdated = productService.findById(id).get();
        if (productUpdated != null) {
            return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.remove(id);
        return new ResponseEntity<>("Delete done!", HttpStatus.OK);
    }
}
