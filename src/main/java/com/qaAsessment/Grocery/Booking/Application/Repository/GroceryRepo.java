package com.qaAsessment.Grocery.Booking.Application.Repository;

import com.qaAsessment.Grocery.Booking.Application.Entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroceryRepo extends JpaRepository<GroceryItem , Integer> {
}
