package com.example.demo.service;

import java.util.Optional;

public interface IGeneraService<E> {
    Iterable<E> findAll();
    Optional<E> findById(Long id);
    Iterable<E> findByName(String name);
    E save(E e);
    boolean remove(Long id);
}
