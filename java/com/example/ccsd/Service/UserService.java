package com.example.ccsd.Service;

import com.example.ccsd.Dto.UserDto;
import com.example.ccsd.Model.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User save(UserDto userDto);
    User findById(Long id); //Method newly added
}
