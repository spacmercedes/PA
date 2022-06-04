package com.example.productmanagement.repo;

import com.example.productmanagement.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name);
}
