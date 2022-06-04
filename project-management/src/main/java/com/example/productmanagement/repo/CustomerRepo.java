package com.example.productmanagement.repo;

import com.example.productmanagement.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByName(String name); //daca clientul nu exista se returneaza null
}
//nu contine multe functii deoarece folosesc stream-uri
//deoarece in mare parte ce era aici, se putea face deja in JPA
//de exemplu findByName si findById sunt by default in JPA repository