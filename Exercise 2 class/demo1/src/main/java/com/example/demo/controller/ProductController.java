package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> search(@RequestParam("search") String name) {
        return new ResponseEntity<>(productService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product Product) {
        return new ResponseEntity<>(productService.save(Product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,
                                       @PathVariable Long id) {
        Optional<Product> productUpdated = productService.findById(id);
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
