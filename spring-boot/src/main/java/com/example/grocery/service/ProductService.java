package com.example.grocery.service;

import com.example.grocery.beans.Product;
import com.example.grocery.dto.ProductDto;
import com.example.grocery.repo.AdminOperationProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    AdminOperationProductRepository adminOperationProductRepository;

    public List<Product> fetchAllProductService() {
        return adminOperationProductRepository.findAll();
    }

    public Product findById(long productId) {
        return adminOperationProductRepository.findByProductId(productId);
    }

    public void productQuantityUpdate(long productId,long quantity){

        Product product = adminOperationProductRepository.findByProductId(productId);

        product.setQuantity(product.getQuantity()-quantity);

        adminOperationProductRepository.save(product);

    }

    public void addProducts(List<ProductDto> products) {

        List<Product> productList = new ArrayList<>();


        for(ProductDto productFromFile: products)
        {
            Optional<Product> product = adminOperationProductRepository.findByProductNameIgnoreCase(productFromFile.getProductName());

            Product newProduct = new Product();
            if(product.isPresent()){
                Product oldProduct = product.get();

                newProduct.setProductId(oldProduct.getProductId());
                newProduct.setProductName(productFromFile.getProductName());
                newProduct.setProductCategory(productFromFile.getProductCategory());
                newProduct.setQuantity(oldProduct.getQuantity() + productFromFile.getQuantity());
                newProduct.setPrice(productFromFile.getPrice());
                System.out.println(newProduct);
            }else{
                newProduct.setProductName(productFromFile.getProductName());
                newProduct.setProductCategory(productFromFile.getProductCategory());
                newProduct.setQuantity(productFromFile.getQuantity());
                newProduct.setPrice(productFromFile.getPrice());
            }
            productList.add(newProduct);
        }

        adminOperationProductRepository.saveAll(productList);

    }


    public List<Product> sortProductsByPrice(String order) {
        if(order.equalsIgnoreCase("asc")){
            return adminOperationProductRepository.findByOrderByPriceAsc();
        }else {
            return adminOperationProductRepository.findByOrderByPriceDesc();
        }
    }

    public List<String> fetchAvailableCategories() {
        return adminOperationProductRepository.fetchCategories();
    }

    public List<Product> filterByCategory(String category) {
        return adminOperationProductRepository.getProductsByProductCategory(category);
    }

    public List<Product> filterProductsByCategoryByOrder(String category, String sortingOrder) {
        if(sortingOrder.equalsIgnoreCase("asc")){
            List<Product> prods  = adminOperationProductRepository.findByProductCategoryOrderByPriceAsc(category);
            System.out.println(prods);
            return prods;
        }else{
            return adminOperationProductRepository.findByProductCategoryOrderByPriceDesc(category);
        }
    }

    public Product findProductByName(String productName){

        return adminOperationProductRepository.findByProductNameIgnoreCase(productName).get();
    }
}
