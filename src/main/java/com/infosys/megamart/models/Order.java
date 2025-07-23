package com.infosys.megamart.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userEmail;
    private String category="NORMAL";
    private double originalPrice;
    private double discountedPrice;
    private double deliveryCharges;
    private int totalQuantity;
    private LocalDate orderedDate;
    private String orderStatus="PENDING";
}
