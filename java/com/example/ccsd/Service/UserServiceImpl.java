//business logic for user management
package com.example.ccsd.Service;

import com.example.ccsd.Model.User;
import com.example.ccsd.Dto.UserDto;
import com.example.ccsd.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepo userRepo;

    public UserServiceImpl (UserRepo userRepo){
        super();
        this.userRepo = userRepo;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), userDto.getFullname(),
                 userDto.getAddressLine1(), userDto.getAddressLine2(),
                userDto.getCity(), userDto.getState(), userDto.getPostalCode());
        return userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

