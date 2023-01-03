package com.example.buildjwt.service;



import com.example.buildjwt.model.User;
import com.example.buildjwt.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    Optional<User> findByUserName(String username);
}
