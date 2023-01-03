package com.example.formregister.repository;

import com.example.formregister.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
}
