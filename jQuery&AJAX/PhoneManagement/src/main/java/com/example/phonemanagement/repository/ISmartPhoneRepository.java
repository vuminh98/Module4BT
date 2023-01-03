package com.example.phonemanagement.repository;

import com.example.phonemanagement.model.SmartPhone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISmartPhoneRepository extends CrudRepository<SmartPhone, Long> {
}
