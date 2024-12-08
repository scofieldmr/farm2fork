package com.example.grocery.dto;

import com.example.grocery.beans.Order;
import com.example.grocery.beans.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;



public class OrderDetailsDto {

    private int orderDetailId;
    private long orderId;
    private long productId;
    private long quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public OrderDetailsDto(int orderDetailId, Long orderId, long productId, long quantity, BigDecimal unitPrice,
			BigDecimal totalPrice) {
		super();
		this.orderDetailId = orderDetailId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}
	public OrderDetailsDto() {
		super();
	}
}
