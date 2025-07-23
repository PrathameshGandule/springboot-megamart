package com.infosys.megamart.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_wishlist")
@Data
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int productId;
    private String userEmail;
}
