package com.example.grocery.controller;

import java.util.List;
import java.util.Map;

import com.example.grocery.beans.Order;
import com.example.grocery.beans.Product;
import com.example.grocery.dto.CartDto;
import com.example.grocery.dto.OrderDetailsDto;
import com.example.grocery.dto.WishlistDto;
import com.example.grocery.service.ProductService;
import com.example.grocery.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.grocery.beans.Customer;
import com.example.grocery.service.CustomerService;


@RequestMapping("/customer")
@RestController
@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
public class CustomerController {


    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    WishlistService wishlistService;





    @PostMapping("/registerCustomer")
    public void addCustomer(@RequestBody Customer customer) {
    	System.out.println(customer);
        customerService.addCustomerService(customer);
    }

    @PutMapping("/updateCustomer")
        public void updateCustomer(@RequestParam Long customerId, @RequestBody Customer customer){
         customerService.updateCustomerDetails(customerId, customer);
    }


    @GetMapping("/fetchCustomerDetailsById")
    public ResponseEntity<Customer> fetchCustomerDetailsById(@RequestParam Long customerId)
    {
        Customer customer = customerService.findById(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @PutMapping("/activateUser")
    public ResponseEntity<String> setUserToActive(@RequestParam String email) {
		String response = customerService.activateUserService(email);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/deactivateUser")
    public ResponseEntity<Customer> setUserToDeactivate(@RequestParam Long customerId) {
        Customer customer = customerService.deactivateUserService(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/searchProductByName")
    public ResponseEntity<Product> productByName(@RequestParam String productName)
    {
        Product product = productService.findProductByName(productName);
        return ResponseEntity.ok(product);
    }



    @GetMapping("/fetchAllProducts")
    public ResponseEntity<List<Product>> fetchAllProducts()
    {
        List<Product> products = productService.fetchAllProductService();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
        String response = customerService.addProductToCartService(cartDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchCart")
    public ResponseEntity<List<CartDto>> fetchCart(@RequestParam Long customerId){
        List<CartDto> cart = customerService.fetchCustomerCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestParam Long customerId, @RequestBody List<CartDto> cartItems) {
            Order order = customerService.createOrderWithDetails(customerId, cartItems);
            return ResponseEntity.ok(order);
    }

    @GetMapping("/orderHistory")
    public ResponseEntity<List<OrderDetailsDto>> fetchorderHistory(@RequestParam Long customerId){
        List<OrderDetailsDto> orderHistory = customerService.fetchOHistory(customerId);
        return new ResponseEntity<>(orderHistory, HttpStatus.OK);
    }

    @DeleteMapping("/removeItemFromCart")
    public ResponseEntity<?> removeItemFromCart(@RequestParam Long customerId, @RequestParam Long productId){
        customerService.deleteItemFromCart(customerId, productId);
        return ResponseEntity.ok().body("Removed from Cart.");
    }

    @DeleteMapping("/clearCartAfterPay")
    public ResponseEntity<?> clearCartAfterPay(@RequestParam Long customerId){
        customerService.deleteAllItemFromCart(customerId);
        return ResponseEntity.ok().body("Removed All Items from Cart.");
    }

    @PostMapping("/addToWishlist")
    public ResponseEntity<String> createWishlist(@RequestBody WishlistDto wishlistDto){
        String response = wishlistService.addProductToWishlist(wishlistDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchWishlist")
    public ResponseEntity<List<WishlistDto>> fectWishlist(@RequestParam Long customerId){
        List<WishlistDto> wishlistDtoList = wishlistService.fetchAllWishlist(customerId);
        return ResponseEntity.ok().body(wishlistDtoList);
    }

    @DeleteMapping("/removeFromWishlist")
    public ResponseEntity<?> removeItemFromWishlist(@RequestParam Long customerId, @RequestParam Long productId){
        wishlistService.deleteItemFromWishlist(customerId, productId);
        return ResponseEntity.ok().body("Removed from Wishlist.");
    }

    @GetMapping("/emailExists")
    public ResponseEntity<Map<String, Boolean>> emailExists(@RequestParam String email)
    {
        Map<String, Boolean> response = customerService.checkIfEmailExists(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("sortProducts")
        public ResponseEntity<List<Product>> sortProducts(@RequestParam String order){
            List<Product> response = productService.sortProductsByPrice(order);
            return ResponseEntity.ok(response);
        }

    @GetMapping("/availableCategories")
    public ResponseEntity<List<String>> getAvailableCategories(){
        List<String> categories = productService.fetchAvailableCategories();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("filterByCategory")
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam String category){

        List<Product> productsByCategory = productService.filterByCategory(category);
        System.out.println(productsByCategory);
        return ResponseEntity.ok(productsByCategory);
    }

    @GetMapping("filterByCategoryByOrder")
    public ResponseEntity<List<Product>> filterProductsByCategoryByOrder(@RequestParam String category, @RequestParam String order)
    {
        List<Product> filteredProducts = productService.filterProductsByCategoryByOrder(category, order);
        return ResponseEntity.ok(filteredProducts);
    }




}
