package com.example.grocery.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;



@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;


    @Column(name = "order_status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'pending'")
    private String orderStatus;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Order(Long orderId, @NotNull LocalDate date, @NotNull Customer customer, String orderStatus,
			BigDecimal totalAmount) {
		super();
		this.orderId = orderId;
		this.date = date;
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.totalAmount = totalAmount;
	}

	public Order() {
		super();
	}


}
