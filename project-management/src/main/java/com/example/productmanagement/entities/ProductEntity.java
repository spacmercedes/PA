package com.example.productmanagement.entities;

import lombok.*;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.Set;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @With
    private Double price;

    @ManyToMany(mappedBy = "products")
    private Set<OrderEntity> orders;

}
