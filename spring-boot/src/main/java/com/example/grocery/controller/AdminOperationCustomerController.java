package com.example.grocery.controller;


import com.example.grocery.beans.Customer;
import com.example.grocery.service.AdminOperationCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/admin/customerManage")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdminOperationCustomerController {

    @Autowired
    AdminOperationCustomerService adminOperationCustomerService;

    @GetMapping("/getCustomerByDomain/")
    public ResponseEntity<List<Customer>> getCustomersByDomain(@RequestParam String domain) {
        List<Customer> customers = adminOperationCustomerService.getCustomerByDomain(domain);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/fetchAllCustomers")
    public ResponseEntity<List<Customer>> fetchAllCustomers(){
        List<Customer> customers = adminOperationCustomerService.fetchALlCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/fetchEmailDomains")
    public ResponseEntity<List<String>> fetchAllEmailDomains()
    {
        List<String> domains = adminOperationCustomerService.getEmailDomains();
        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    @GetMapping("/emailDomainCount")
    public Map<String, Long> getCustomerCountByEmailDomain() {
        Map<String, Long> domainCounts = adminOperationCustomerService.getCustomerCountByEmailDomain();
        System.out.println(domainCounts);
        return domainCounts;
    }
}
