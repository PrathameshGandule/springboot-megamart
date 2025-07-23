package com.infosys.megamart.controllers;

import com.infosys.megamart.dtos.CartRequest;
import com.infosys.megamart.dtos.Response;
import com.infosys.megamart.models.Cart;
import com.infosys.megamart.services.CartServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartServices cartServices;

    @PostMapping("")
    public ResponseEntity<Response> addItemToCart(Authentication authentication, @Valid @RequestBody CartRequest cartRequest){
        String userEmail = authentication.getName();
        String message = cartServices.addCartItem(userEmail, cartRequest);
        return ResponseEntity.ok(new Response(200, message));
    }

    @GetMapping("")
    public List<Cart> getWholeCart(Authentication authentication){
        String userEmail = authentication.getName();
        return cartServices.getUserCart(userEmail);
    }

    @PutMapping("")
    public ResponseEntity<Response> updateProductQuantity(Authentication authentication, @Valid @RequestBody CartRequest cartRequest){
        String userEmail = authentication.getName();
        String message = cartServices.updateProductQuantity(userEmail, cartRequest);
        return ResponseEntity.ok(new Response(200, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> removeCartItem(Authentication authentication, @PathVariable int id){
        String userEmail = authentication.getName();
        String message = cartServices.removeProduct(userEmail, id);
        return ResponseEntity.ok(new Response(200, message));
    }
}
