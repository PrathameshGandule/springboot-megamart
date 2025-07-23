package com.infosys.megamart.services;

import com.infosys.megamart.dtos.AddProductRequest;
import com.infosys.megamart.exceptions.ProductNotFoundException;
import com.infosys.megamart.models.Product;
import com.infosys.megamart.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepo productRepo;

    public Product findProductById(int id){
        return productRepo.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id "+id+" not found"));
    }

    public List<Product> findProductByName(String productName){
        return productRepo.findByProductName(productName);
    }

    public List<Product> findAllProducts(){
        return productRepo.findAll();
    }

    public void addProduct(AddProductRequest addProductRequest){
        Product product = new Product();
        product.setProductName(addProductRequest.getProductName());
        product.setDescription(addProductRequest.getDescription());
        product.setPrice(addProductRequest.getPrice());
        product.setDiscount(addProductRequest.getDiscount());
        product.setDeliveryCharges(addProductRequest.getDeliveryCharges());
        product.setAvgRating(addProductRequest.getAvgRating());
        product.setSellerName(addProductRequest.getSellerName());
        productRepo.save(product);
    }
}
