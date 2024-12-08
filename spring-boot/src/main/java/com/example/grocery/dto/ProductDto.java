package com.example.grocery.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private String productName;

    private String productCategory;

    private long quantity;

    private double price;

//    public ProductDto(String productName, String productCategory, long quantity,
//                   double price) {
//        super();
//        this.productName = productName;
//        this.productCategory = productCategory;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public String getProductCategory() {
//        return productCategory;
//    }
//
//    public long getQuantity() {
//        return quantity;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public void setProductCategory(String productCategory) {
//        this.productCategory = productCategory;
//    }
//
//    public void setQuantity(long quantity) {
//        this.quantity = quantity;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "ProductDto{" +
//                "productName='" + productName + '\'' +
//                ", productCategory='" + productCategory + '\'' +
//                ", quantity=" + quantity +
//                ", price=" + price +
//                '}';
//    }
}
