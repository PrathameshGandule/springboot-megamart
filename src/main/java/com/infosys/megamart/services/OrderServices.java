package com.infosys.megamart.services;

import com.infosys.megamart.models.Cart;
import com.infosys.megamart.models.Order;
import com.infosys.megamart.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServices {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartServices cartServices;

    public Order placeOrder(String userEmail){
        List<Cart> cartList = cartServices.getUserCart(userEmail);
        if(cartList.isEmpty()){
            return new Order();
        }
        double totalOriginalPrice = 0;
        double totalDiscountedPrice = 0;
        int totalQuantity = 0;
        double toatlDeliveryCharges = 0;
        for(Cart cart: cartList){
            totalQuantity += cart.getQuantity();
            totalOriginalPrice += cart.getOriginalPrice() * cart.getQuantity();
            totalDiscountedPrice += cart.getCartOfferPrice() * cart.getQuantity();
            toatlDeliveryCharges += cart.getDeliveryCharges();
        }
        Order order = new Order();
        order.setUserEmail(userEmail);
        order.setOriginalPrice(totalOriginalPrice);
        order.setDiscountedPrice(totalDiscountedPrice);
        order.setTotalQuantity(totalQuantity);
        order.setOrderedDate(LocalDate.now());
        order.setDeliveryCharges(toatlDeliveryCharges);
        orderRepo.save(order);
        cartServices.deleteUserCart(userEmail);
        return order;
    }

    public List<Order> getAllOrders(String userEmail){
        return orderRepo.findByUserEmail(userEmail);
    }
}
