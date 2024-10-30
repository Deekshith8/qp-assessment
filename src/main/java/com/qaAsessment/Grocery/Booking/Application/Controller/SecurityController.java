package com.qaAsessment.Grocery.Booking.Application.Controller;


import com.qaAsessment.Grocery.Booking.Application.Entity.User;
import com.qaAsessment.Grocery.Booking.Application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        return userService.saveUser(user);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/dummy")
    public void dummy(){
        System.out.println("HIII" );
    }
}
