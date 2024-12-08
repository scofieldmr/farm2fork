package com.example.grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CartDto {

    private long customerId;
    private long productId;
    private String productName;
    private long quantity;
    private double price;
    public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	private long totalPrice;
	public CartDto(long customerId, long productId, String productName, long quantity, double price, long totalPrice) {
		super();
		this.customerId = customerId;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}
	public CartDto() {
		super();
	}

}
