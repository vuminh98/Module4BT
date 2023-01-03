package com.example.blogsecurity.controller;

import com.example.blogsecurity.model.Blog;
import com.example.blogsecurity.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> findAll() {
        return new ResponseEntity<>(blogService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Blog>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@RequestBody Blog blog,
                                           @PathVariable Long id) {
        Optional<Blog> blogUpdated = blogService.findById(id);
        if (blogUpdated != null) {
            return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        blogService.remove(id);
        return new ResponseEntity<>("Delete done!", HttpStatus.OK);
    }
}
