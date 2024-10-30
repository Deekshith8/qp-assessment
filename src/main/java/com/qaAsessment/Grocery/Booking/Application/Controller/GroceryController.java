package com.qaAsessment.Grocery.Booking.Application.Controller;


import com.qaAsessment.Grocery.Booking.Application.Entity.GroceryItem;
import com.qaAsessment.Grocery.Booking.Application.Service.GroceryService;
import com.qaAsessment.Grocery.Booking.Application.Utils.Order;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groceryShop")
public class GroceryController {


    @Autowired
    public GroceryService groceryService;


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addItem")
    public ResponseEntity addGroceryItem(@RequestBody GroceryItem item) {
        return groceryService.addItem(item);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN' , 'USER')")
    @GetMapping("/allItems")
    public ResponseEntity allGroceryItems() {
        return groceryService.getAllGroceryItems();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getGroceryById/{id}")
    public ResponseEntity getGroceryById(@PathVariable int id) {
        return groceryService.getGroceryById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/removeItem/{id}")
    public ResponseEntity removeGroceryById(@PathVariable int id) {
        return groceryService.removeGroceryById(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/updateItem")
    public ResponseEntity updateItem(@RequestBody GroceryItem item) {
        return groceryService.updateItem(item);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/updateQuantity/{id}/{quanity}")
    public ResponseEntity updateItemQuatity(@PathVariable int id, @PathVariable int quantiy) {
        return groceryService.updateItemQuantity(id, quantiy);
    }


    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/orderGroceries")
    public ResponseEntity orderGrocery( @RequestBody List<Order> orderGroceries) {
        return groceryService.orderGrocery(orderGroceries);
    }
}
