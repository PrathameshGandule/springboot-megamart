package com.infosys.megamart.services;

import com.infosys.megamart.dtos.RegisterRequest;
import com.infosys.megamart.exceptions.UserNotFoundException;
import com.infosys.megamart.models.User;
import com.infosys.megamart.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.firstName);
        user.setLastName(userRequest.lastName);
        user.setEmail(userRequest.email);
        user.setPassword(passwordEncoder.encode(userRequest.password));
        userRepo.save(user);
        return "new user registered successfully";
    }

    public User findUserByEmail(String email){
        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        return user;
    }

}
