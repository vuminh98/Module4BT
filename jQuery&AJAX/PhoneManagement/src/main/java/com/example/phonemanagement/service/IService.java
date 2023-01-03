package com.example.phonemanagement.service;

import java.util.Optional;

public interface IService<E> {

    Iterable<E> findAll();

    Optional<E> findById(Long id);

    E save(E e);

    void remove(Long id);
}
