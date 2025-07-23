package com.infosys.megamart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userEmail;

    @Column(nullable = false)
    private int productId;
    private int quantity;
    private double originalPrice;
    private double cartOfferPrice;
    private double deliveryCharges;
}
