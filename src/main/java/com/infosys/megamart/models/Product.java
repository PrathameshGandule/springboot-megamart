package com.infosys.megamart.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String productName;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double price;
    private double discount;
    private double deliveryCharges;
    private int avgRating;
    private String sellerName;
}

