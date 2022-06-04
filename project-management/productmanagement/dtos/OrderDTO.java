package com.example.productmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private CustomerDTO customer;
    private List<ProductDTO> products;
}
