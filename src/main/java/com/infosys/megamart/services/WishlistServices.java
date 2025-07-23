package com.infosys.megamart.services;

import com.infosys.megamart.exceptions.WishlistItemNotFound;
import com.infosys.megamart.models.Product;
import com.infosys.megamart.models.Wishlist;
import com.infosys.megamart.repos.WishlistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServices {

    @Autowired
    private WishlistRepo wishlistRepo;
    @Autowired
    private ProductServices productServices;

    public String addProductToWishlist(String userEmail, int productId){
        Optional<Wishlist> existingWishlist = wishlistRepo.findByUserEmailAndProductId(userEmail, productId);
        if(existingWishlist.isPresent()){
            return "product with id"+productId+" already exists in your wishlist";
        }
        Product product = productServices.findProductById(productId);
        Wishlist wishlist = new Wishlist();
        wishlist.setUserEmail(userEmail);
        wishlist.setProductId(product.getId());
        wishlistRepo.save(wishlist);
        return "product with id "+productId+" added to your wishlist";
    }

    public String deleteProductFromWishlist(String userEmail, int productId){
        Wishlist wishlist = wishlistRepo.findByUserEmailAndProductId(userEmail, productId)
                .orElseThrow(() -> new WishlistItemNotFound("wishlist product with id"+productId+" not found"));
        wishlistRepo.delete(wishlist);
        return "product with id "+wishlist.getProductId()+" removed from your wishlist";
    }

    public List<Product> getWholeWishlist(String userEmail){
        List<Wishlist> wishlists = wishlistRepo.findByUserEmail(userEmail);
        List<Product> products = new java.util.ArrayList<>(List.of());
        for (Wishlist wishlist: wishlists){
            Product product = productServices.findProductById(wishlist.getProductId());
            products.add(product);
        }
        return products;
    }
}
