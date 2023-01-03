package com.example.buildjwt.service;

import com.example.buildjwt.model.Role;

public interface IRoleService extends IGeneralService<Role>{
    Role findByName(String name);
}
