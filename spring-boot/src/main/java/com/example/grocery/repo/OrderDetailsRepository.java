package com.example.grocery.repo;

import com.example.grocery.beans.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {


    @Query("SELECT od FROM OrderDetails od WHERE od.order.orderId = :orderId")
    List<OrderDetails> findByOrderId(@Param("orderId") Long orderId);
}
