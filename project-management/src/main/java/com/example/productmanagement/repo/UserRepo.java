package com.example.productmanagement.repo;

import com.example.productmanagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByEmail(String email);
}
