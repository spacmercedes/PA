package com.example.productmanagement.services;

import com.example.productmanagement.dtos.ProductDTO;
import com.example.productmanagement.dtos.StatisticsDTO;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsByCategory(String category);

    StatisticsDTO getStatistics();

}
