package com.example.grocery.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.grocery.beans.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
      Optional<Customer> findByEmailAndPassword(String email, String password);

      Customer findByCustomerId(Long customerId);


      Optional<Customer> findByEmail(String email);
}
