package com.infosys.megamart.repos;

import com.infosys.megamart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserEmail(String userEmail);
    Optional<Cart> findByUserEmailAndProductId(String userEmail, int productId);
    void deleteByUserEmail(String userEmail);
}

