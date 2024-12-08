package com.example.grocery.service;

import com.example.grocery.beans.*;
import com.example.grocery.dto.CartDto;
import com.example.grocery.dto.OrderDetailsDto;
import com.example.grocery.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AdminOperationProductRepository adminOperationProductRepository;

    @Autowired
    CustomerCartRepository customerCartRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    public void addCustomerService(Customer customer) {
        System.out.println(customer);
        customerRepository.save(customer);
    }

    public String activateUserService(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);

        Customer customerUpdate = null;
        if(customer.isPresent()){
            customerUpdate = customer.get();
            customerUpdate.setActiveUser("Yes");
            customerRepository.save(customerUpdate);
            return "Account Activated";
        }else{
            return "No such user exists";
        }

    }


    public Customer deactivateUserService(Long customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        customer.setActiveUser("No");
        return customerRepository.save(customer);
    }



    public String addProductToCartService(CartDto cartDto) {
        Cart product = customerCartRepository.existsByProductId(cartDto.getCustomerId(), cartDto.getProductId());
        if(product != null){
            return "Already present in the cart";
        }
        else {
            Cart cart = new Cart();
            cart.setCustomer(findById(cartDto.getCustomerId()));
            cart.setProduct(productService.findById(cartDto.getProductId()));
            cart.setProductName(cartDto.getProductName());
            cart.setQuantity(cartDto.getQuantity());
            cart.setPrice(cartDto.getPrice());
            cart.setTotalPrice(cartDto.getTotalPrice());
            customerCartRepository.save(cart);
            return "Added to the cart";
        }
    }

    public Customer findById(Long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public List<CartDto> fetchCustomerCart(Long customerId) {
        List<Cart> cart = customerCartRepository.findByCustomerId(customerId);
        List<CartDto> cartDto = new ArrayList<>();
        CartDto product = null;
        for(Cart prod: cart){
            product = new CartDto(prod.getCustomer().getCustomerId(), prod.getProduct().getProductId(),
                    prod.getProductName(), prod.getQuantity(), prod.getPrice(), prod.getTotalPrice());
            cartDto.add(product);
        }
        return cartDto;
    }

    public Order createOrderWithDetails(Long customerId,List<CartDto> cartItems) {


        Order order = new Order();
        order.setCustomer(customerRepository.findByCustomerId(customerId));
        order.setDate(LocalDate.now());
        order.setOrderStatus("paid");
        order.setTotalAmount(BigDecimal.ZERO);
        orderRepository.save(order);



        BigDecimal totalAmount = BigDecimal.ZERO;

        List<OrderDetailsDto> orderDetailsDto = new ArrayList<>();


        for (CartDto item : cartItems) {
            totalAmount = totalAmount.add(BigDecimal.valueOf(item.getTotalPrice()));

            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setOrder(order);
            orderDetails.setProductId(productService.findById(item.getProductId()));
            orderDetails.setQuantity(item.getQuantity());
            productService.productQuantityUpdate(item.getProductId(),item.getQuantity());
            orderDetails.setUnitPrice(BigDecimal.valueOf(item.getPrice()));
            orderDetails.setTotalPrice(BigDecimal.valueOf(item.getTotalPrice()));


            orderDetailsRepository.save(orderDetails);
        }

        order.setTotalAmount(totalAmount);
        orderRepository.save(order);

        return order;
    }

    public List<OrderDetailsDto> fetchOHistory(Long customerId) {
        List<OrderDetailsDto> orderList = new ArrayList<>();
        List<Long> orderIdList = orderRepository.findOrderIdByCustomerId(customerId);


        for (Long orderId : orderIdList) {
            List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(orderId);

            for (OrderDetails orderDetails : orderDetailsList) {
                OrderDetailsDto orderDetailsDto = new OrderDetailsDto(
                        orderDetails.getOrderDetailId(),
                        orderDetails.getOrder().getOrderId(),
                        orderDetails.getProductId().getProductId(),
                        orderDetails.getQuantity(),
                        orderDetails.getUnitPrice(),
                        orderDetails.getTotalPrice()
                );
                orderList.add(orderDetailsDto);
            }
        }

            return orderList;
    }

    public void deleteItemFromCart(Long customerId, Long productId) {
        customerCartRepository.deleteByProductId(customerId, productId);
    }

    public void deleteAllItemFromCart(Long customerId) {
        customerCartRepository.deleteAllItemFromCartByCustomerId(customerId);
    }

    public void updateCustomerDetails(Long customerId, Customer customer) {
        Customer customerTemp = customerRepository.findByCustomerId(customerId);
        customerTemp.setCustomerName(customer.getCustomerName());
        customerTemp.setEmail(customer.getEmail());
        customerTemp.setAddress(customer.getAddress());
        customerTemp.setContactNumber(customer.getContactNumber());
        customerTemp.setEmailDomain(customer.getEmailDomain());
        customerTemp.setPassword(customer.getPassword());
        customerRepository.save(customerTemp);

    }


    public Map<String, Boolean> checkIfEmailExists(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        boolean exists = customer.isPresent();
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
}
