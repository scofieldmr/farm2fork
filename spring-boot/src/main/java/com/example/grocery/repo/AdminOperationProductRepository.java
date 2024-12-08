package com.example.grocery.repo;

import com.example.grocery.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminOperationProductRepository extends JpaRepository<Product, Long> {
    Product findByProductId(long productId);

    List<Product> findByOrderByPriceAsc();

    List<Product> findByOrderByPriceDesc();

    @Query("SELECT DISTINCT p.productCategory FROM Product p")
    List<String> fetchCategories();

    @Query("SELECT p FROM Product p WHERE  p.productCategory = :category")
    List<Product> getProductsByProductCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE p.productCategory = :category ORDER BY p.price ASC")
    List<Product> findByProductCategoryOrderByPriceAsc(String category);


    @Query("SELECT p FROM Product p WHERE p.productCategory = :category ORDER BY p.price DESC")
    List<Product> findByProductCategoryOrderByPriceDesc(String category);

    Optional<Product> findByProductNameIgnoreCase(String productName);
}
