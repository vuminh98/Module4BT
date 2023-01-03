package com.example.songvalidation.service;

import java.util.Optional;

public interface IService<E> {
    Iterable<E> findAll();

    Optional<E> findById(Long id);

    void save(E e);

    void remove(Long id);
}
