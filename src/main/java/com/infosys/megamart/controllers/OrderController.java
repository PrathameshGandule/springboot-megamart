package com.infosys.megamart.controllers;

import com.infosys.megamart.models.Order;
import com.infosys.megamart.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServices orderServices;

    @PostMapping("")
    public Order placeOrder(Authentication authentication){
        String userEmail = authentication.getName();
        return orderServices.placeOrder(userEmail);
    }

    @GetMapping("")
    public List<Order> getAllOrdersByEmail(Authentication authentication){
        String userEmail = authentication.getName();
        return orderServices.getAllOrders(userEmail);
    }
}
