package com.qaAsessment.Grocery.Booking.Application.Utils;

import com.qaAsessment.Grocery.Booking.Application.Entity.Role;
import com.qaAsessment.Grocery.Booking.Application.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String userName;

    private String passWord;

    private String role;

    public CustomUserDetails(User user){
        this.userName = user.getUserName();
        this.passWord = user.getPassword();
        this.role = user.getRole();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
}
