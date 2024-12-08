package com.example.grocery.repo;

import com.example.grocery.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminOperationCustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getCustomersByEmailDomain(String domain);

    @Query("SELECT DISTINCT c.emailDomain FROM Customer c")
    List<String> findDistinctByEmailDomain();


    @Query("SELECT c.emailDomain AS domain, COUNT(c) AS count FROM Customer c GROUP BY c.emailDomain ORDER BY count ASC")
    List<Object[]> findCustomerCountByEmailDomain();
}
