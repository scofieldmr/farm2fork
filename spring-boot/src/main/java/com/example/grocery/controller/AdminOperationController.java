package com.example.grocery.controller;


import com.example.grocery.beans.Admin;
import com.example.grocery.beans.Customer;
import com.example.grocery.beans.Product;
import com.example.grocery.dto.ProductDto;
import com.example.grocery.service.AdminOperationService;
import com.example.grocery.service.ProductService;
import com.example.grocery.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RequestMapping("/admin")
@RestController
public class AdminOperationController {

    @Autowired
    AdminOperationService adminOperationService;

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;

    @PutMapping("/updateAdmin")
    public void updateCustomer(@RequestParam Long adminId, @RequestBody Admin admin){
        adminOperationService.updateAdminDetails(adminId, admin);
    }


    @GetMapping("/fetchAdminDetailsById")
    public ResponseEntity<Admin> fetchCustomerDetailsById(@RequestParam Long adminId)
    {
        Admin admin = adminOperationService.findByAdminId(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/deactivateUser")
    public ResponseEntity<Admin> setUserToDeactivate(@RequestParam Long adminId) {
        Admin admin = adminOperationService.deactivateUserService(adminId);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/products/addProduct")
    public void addProduct(@RequestBody ProductDto product){
        System.out.println(product);
        adminOperationService.addProductService(product);
    }

    @PostMapping("/products/addAll")
    public ResponseEntity<?> addProducts(@RequestBody List<ProductDto> products) {
        System.out.println(products);
        productService.addProducts(products);
        return ResponseEntity.ok("Products Added");
    }

    @GetMapping("/products/fetchAllProducts")
    public ResponseEntity<List<Product>> fetchAllProducts()
    {
        List<Product> products = productService.fetchAllProductService();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/products/deleteProductById")
    public void deleteProduct(@RequestParam long id){
        adminOperationService.deleteProductById(id);
    }

    @PutMapping("/products/updateProductById")
    public void updateProduct(@RequestParam long id, @RequestBody Product product){
        adminOperationService.updateProductById(id, product);
    }

    @GetMapping("/products/product-demand")
    public List<Map<String, Object>> getProductWishlistDemand() {
        return wishlistService.getProductWishlistDemand();
    }



}
