package com.infosys.megamart.services;

import com.infosys.megamart.dtos.CartRequest;
import com.infosys.megamart.exceptions.CartItemNotFoundException;
import com.infosys.megamart.models.Cart;
import com.infosys.megamart.models.Product;
import com.infosys.megamart.repos.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServices {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductServices productServices;

    public String addCartItem(String userEmail, CartRequest cartRequest){
        Optional<Cart> existingCartItem = cartRepo.findByUserEmailAndProductId(userEmail, cartRequest.getProductId());
        if(existingCartItem.isPresent()){
            int quantity = existingCartItem.get().getQuantity();
            existingCartItem.get().setQuantity(quantity + cartRequest.getQuantity());
            cartRepo.save(existingCartItem.get());
            return "increased quantity by "+cartRequest.getQuantity()+" of existing product with id "+cartRequest.getProductId();
        }
        Cart cart = new Cart();
        Product product = productServices.findProductById(cartRequest.getProductId());
        double discountedPrice = product.getPrice() * product.getDiscount() / 100;
        cart.setUserEmail(userEmail);
        cart.setProductId(product.getId());
        cart.setQuantity(cartRequest.getQuantity());
        cart.setOriginalPrice(product.getPrice());
        cart.setCartOfferPrice(product.getPrice() - discountedPrice);
        cart.setDeliveryCharges(product.getDeliveryCharges());
        cartRepo.save(cart);
        return "product with id "+cart.getProductId()+" added successfully to "+cart.getUserEmail()+ "'s cart";
    }

    public List<Cart> getUserCart(String userEmail){
        return cartRepo.findByUserEmail(userEmail);
    }

    public String removeProduct(String userEmail, int productId){
        Cart cartItem = cartRepo.findByUserEmailAndProductId(userEmail, productId)
                .orElseThrow(() -> new CartItemNotFoundException("cart item not found"));
        cartRepo.delete(cartItem);
        return "cart item with id"+cartItem.getProductId()+" removed";
    }

    public String updateProductQuantity(String userEmail, CartRequest cartRequest){
        Cart cartItem = cartRepo.findByUserEmailAndProductId(userEmail, cartRequest.getProductId())
                .orElseThrow(() -> new CartItemNotFoundException("cart item not found"));
        cartItem.setQuantity(cartRequest.getQuantity());
        cartRepo.save(cartItem);
        return "quantity of product "+cartItem.getProductId()+" updated to "+ cartRequest.getQuantity();
    }

    @Transactional
    public void deleteUserCart(String userEmail){
        cartRepo.deleteByUserEmail(userEmail);
    }
}
