package com.infosys.megamart.controllers;

import com.infosys.megamart.dtos.Response;
import com.infosys.megamart.models.Product;
import com.infosys.megamart.models.Wishlist;
import com.infosys.megamart.services.WishlistServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistServices wishlistServices;

    @PostMapping("/{productId}")
    public ResponseEntity<Response> addProductToWishlist(Authentication authentication,@PathVariable int productId){
        String userEmail = authentication.getName();
        String message = wishlistServices.addProductToWishlist(userEmail, productId);
        return ResponseEntity.ok(new Response(200, message));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Response> deleteProductFromWishlist(Authentication authentication,@PathVariable int productId){
        String userEmail = authentication.getName();
        String message = wishlistServices.deleteProductFromWishlist(userEmail, productId);
        return ResponseEntity.ok(new Response(200, message));
    }

    @GetMapping("")
    public List<Product> getWholeWishlist(Authentication authentication){
        String userEmail = authentication.getName();
        return wishlistServices.getWholeWishlist(userEmail);
    }
}
