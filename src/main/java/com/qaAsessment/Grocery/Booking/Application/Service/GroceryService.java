package com.qaAsessment.Grocery.Booking.Application.Service;


import com.qaAsessment.Grocery.Booking.Application.Entity.GroceryItem;
import com.qaAsessment.Grocery.Booking.Application.Repository.GroceryRepo;
import com.qaAsessment.Grocery.Booking.Application.Utils.Order;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryService {

    @Autowired
    private GroceryRepo groceryRepo;

    public ResponseEntity addItem(GroceryItem item){
        GroceryItem savedItem = groceryRepo.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }


    public ResponseEntity getAllGroceryItems(){
        List<GroceryItem> allItems = groceryRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allItems);
    }

    public ResponseEntity getGroceryById(int id){
        Optional<GroceryItem> item = groceryRepo.findById(id);
        if(item.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    public ResponseEntity removeGroceryById(int id){
        if(!groceryRepo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found");
        }
        groceryRepo.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Item is Removed...");
    }

    public ResponseEntity updateItem(GroceryItem item){
        GroceryItem prevItem = groceryRepo.findById(item.getId()).get();

        prevItem.setItemName(item.getItemName());
        prevItem.setPrice(item.getPrice());
        prevItem.setAvailableQuantity(item.getAvailableQuantity());

        item = groceryRepo.save(prevItem);

        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    public ResponseEntity updateItemQuantity(int id , int quantity){
        if(!groceryRepo.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Item Found");
        }

        GroceryItem item = groceryRepo.findById(id).get();
        item.setAvailableQuantity(quantity);
        return ResponseEntity.status(HttpStatus.OK).body(groceryRepo.save(item));
    }

    public ResponseEntity orderGrocery(List<Order> orders){
        if(!validOrder(orders)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Order , Please Retry!!!");
        }
        for(Order order : orders){
            GroceryItem item = groceryRepo.findById(order.getItemId()).get();
            item.setAvailableQuantity(item.getAvailableQuantity() - order.getQuantiy());
            groceryRepo.save(item);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Your Order has been Confirmed....Have a good day...");
    }

    private boolean validOrder(List<Order> orders){
        for(Order order : orders){
          if(groceryRepo.existsById(order.getItemId())){
              GroceryItem item = groceryRepo.findById(order.getItemId()).get();
              if(item.getAvailableQuantity() < order.getQuantiy()){
                  return false;
              }
          }else{
              return false;
          }
        }

        return true;
    }

}
