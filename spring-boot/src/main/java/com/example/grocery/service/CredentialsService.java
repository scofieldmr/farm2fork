package com.example.grocery.service;

import com.example.grocery.beans.Admin;
import com.example.grocery.beans.Customer;
import com.example.grocery.repo.AdminRepository;
import com.example.grocery.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CredentialsService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AdminRepository adminRepository;

    public Map<String, Object> authenticate(String email, String password) {
        Optional<Customer> user = customerRepository.findByEmailAndPassword(email, password);
        Optional<Admin> admin = adminRepository.findByEmailAndPassword(email, password);
        Map<String, Object> response = new HashMap<>();
        System.out.println(user);
        if (user.isPresent()){
            Customer authenticatedUser = user.get();
            if (authenticatedUser.getActiveUser().equalsIgnoreCase("yes")) {
                System.out.println(authenticatedUser);
                response.put("status", "success");
                response.put("message", "customer");
                response.put("userId", authenticatedUser.getCustomerId());
            } else {
                response.put("status", "deactivated");
                response.put("message", "Your account has been deactivated.");
            }
        } else if(admin.isPresent()){
            Admin authenticatedAdmin = admin.get();
            if (authenticatedAdmin.getActiveUser().equalsIgnoreCase("yes")) {
                System.out.println(authenticatedAdmin);
                response.put("status", "success");
                response.put("message", "admin");
                response.put("userId", authenticatedAdmin.getAdminId());
            } else {
                response.put("status", "deactivated");
                response.put("message", "Your account has been deactivated.");
            }
        }
        else {
            response.put("status", "error");
            response.put("message", "Email or password not valid");
        }
        return response;

    }
}
