package com.qaAsessment.Grocery.Booking.Application.Service;

import com.qaAsessment.Grocery.Booking.Application.Entity.User;
import com.qaAsessment.Grocery.Booking.Application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       User savedUser =  userRepo.save(user);
       return   ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
}
