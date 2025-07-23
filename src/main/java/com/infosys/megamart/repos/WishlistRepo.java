package com.infosys.megamart.repos;

import com.infosys.megamart.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer> {
    Optional<Wishlist> findByUserEmailAndProductId(String userEmail, int productId);
    List<Wishlist> findByUserEmail(String userEmail);
}
