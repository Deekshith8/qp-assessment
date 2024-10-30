package com.qaAsessment.Grocery.Booking.Application.Repository;

import com.qaAsessment.Grocery.Booking.Application.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

   User findByuserName(String userName);
}
