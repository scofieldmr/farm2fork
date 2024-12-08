package com.example.grocery.beans;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table(name="product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;

	@NotNull
	@Column(name = "product_name")
	private String productName;

	@NotNull
	@Column(name = "product_category")
	private String productCategory;

	@NotNull
	@Column(name = "quantity")
	private long quantity;

	@NotNull
	@Column(name = "price")
	private double price;

	public Product() {
		super();
	}

	public Product(long productId, @NotNull String productName, @NotNull String productCategory, @NotNull long quantity,
				   @NotNull double price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCategory = productCategory;
		this.quantity = quantity;
		this.price = price;
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

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productCategory='" + productCategory + '\'' +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}
}
