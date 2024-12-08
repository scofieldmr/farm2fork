package com.example.grocery.beans;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;



@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int orderDetailId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "product_id")
    private Product productId;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @NotNull
    @Column(name = "price")
    private BigDecimal unitPrice;

    public int getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
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

	@NotNull
    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

	public OrderDetails(int orderDetailId, Order order, Product productId, long quantity, @NotNull BigDecimal unitPrice,
			@NotNull BigDecimal totalPrice) {
		super();
		this.orderDetailId = orderDetailId;
		this.order = order;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
	}

	public OrderDetails() {
		super();
	}
}
