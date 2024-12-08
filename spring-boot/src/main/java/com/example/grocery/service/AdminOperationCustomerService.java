package com.example.grocery.service;

import com.example.grocery.beans.Customer;
import com.example.grocery.repo.AdminOperationCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminOperationCustomerService {

    @Autowired
    AdminOperationCustomerRepository adminOperationCustomerRepository;

    public List<Customer> getCustomerByDomain(String domain) {
        return adminOperationCustomerRepository.getCustomersByEmailDomain(domain);
    }

    public List<Customer> fetchALlCustomers() {
        return adminOperationCustomerRepository.findAll();
    }

    public List<String> getEmailDomains()
    {
        return adminOperationCustomerRepository.findDistinctByEmailDomain();
    }

    public Map<String, Long> getCustomerCountByEmailDomain() {
        List<Object[]> results = adminOperationCustomerRepository.findCustomerCountByEmailDomain();
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));
    }
}
