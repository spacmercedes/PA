package com.example.productmanagement.repo;

import com.example.productmanagement.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
}
