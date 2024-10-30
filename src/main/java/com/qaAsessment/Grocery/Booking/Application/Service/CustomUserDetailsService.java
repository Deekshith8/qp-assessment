package com.qaAsessment.Grocery.Booking.Application.Service;

import com.qaAsessment.Grocery.Booking.Application.Entity.User;
import com.qaAsessment.Grocery.Booking.Application.Repository.UserRepo;
import com.qaAsessment.Grocery.Booking.Application.Utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = Optional.ofNullable(userRepo.findByuserName(username));

        return user.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("User not found with user name :" + username));
    }

}
