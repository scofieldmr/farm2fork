package com.example.grocery.repo;

import com.example.grocery.beans.Wishlist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    @Query("SELECT w FROM Wishlist w WHERE w.customer.customerId = :customerId")
    List<Wishlist> findByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Wishlist w WHERE w.customer.customerId = :customerId AND w.product.productId = :productId")
    void deletedByProductId(@Param("customerId") Long customerId, @Param("productId")Long productId);


    @Query("SELECT w FROM Wishlist w WHERE w.customer.customerId = :customerId AND w.product.productId = :productId")
    Wishlist existsByCustomerId(@Param("customerId") long customerId, @Param("productId") long productId);


    @Query("SELECT w.product.productId, w.product.productName, w.product.productCategory, COUNT(w) as wishlistCount " +
            "FROM Wishlist w " +
            "GROUP BY w.product.productId, w.product.productName, w.product.productCategory")
    List<Object[]> findProductWishlistDemand();
}
