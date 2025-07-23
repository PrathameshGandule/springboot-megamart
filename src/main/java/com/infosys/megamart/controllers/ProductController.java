package com.infosys.megamart.controllers;

import com.infosys.megamart.dtos.AddProductRequest;
import com.infosys.megamart.dtos.Response;
import com.infosys.megamart.models.Product;
import com.infosys.megamart.services.ProductServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping("")
    public ResponseEntity<Response> addProduct(@Valid @RequestBody AddProductRequest addProductRequest){
        productServices.addProduct(addProductRequest);
        return ResponseEntity.ok(new Response(200, "product added successfully"));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productServices.findProductById(id);
    }

    @GetMapping("")
    public List<Product> getProductByName(@RequestParam String name){
        return productServices.findProductByName(name);
    }

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productServices.findAllProducts();
    }
}
