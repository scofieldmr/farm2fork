package com.example.grocery.beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart")

public class Cart {

    public Cart(long cartId, Customer customer, Product product, String productName, long quantity, double price,
			long totalPrice) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.product = product;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne
    private Customer customer;

    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private Product product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "total_price", columnDefinition = "integer default 0")
    private long totalPrice;

	public Cart() {
		super();
	}
}
