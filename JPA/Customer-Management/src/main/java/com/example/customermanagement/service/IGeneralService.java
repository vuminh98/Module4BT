package com.example.customermanagement.service;

import com.example.customermanagement.exception.DuplicateLastNameException;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id) throws Exception;

    void save(T t) throws DuplicateLastNameException;

    void remove(Long id);

}
