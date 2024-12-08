package com.example.grocery.repo;

import com.example.grocery.beans.Cart;
import com.example.grocery.beans.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerCartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
    List<Cart> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT c FROM Cart c WHERE c.product.productId = :productId")
    Cart findByProductId(@Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    void deleteByProductId(@Param("customerId") Long customerId, @Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE c.customer.customerId = :customerId")
    void deleteAllItemFromCartByCustomerId(Long customerId);

    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId AND c.product.productId = :productId")
    Cart existsByProductId(@Param("customerId") Long customerId, @Param("productId") Long productId);
}
