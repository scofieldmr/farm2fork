package com.example.grocery.repo;

import com.example.grocery.beans.Order;
import com.example.grocery.beans.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.customer.customerId = :customerId")
    List<Order> findByCustomerId(@Param("customerId") Long customerId);


    @Query("SELECT o.orderId FROM Order o WHERE o.customer.customerId = :customerId")
    List<Long> findOrderIdByCustomerId(@Param("customerId") Long customerId);
}
