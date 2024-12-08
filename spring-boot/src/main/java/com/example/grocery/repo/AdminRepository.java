package com.example.grocery.repo;

import com.example.grocery.beans.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndPassword(String email, String password);

    Admin findByAdminId(Long adminId);
}
