package com.example.productmanagement.services;

import com.example.productmanagement.dtos.ProductDTO;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductsByCategory(String category);

    DoubleSummaryStatistics getStatistics();

    void addNewProduct(ProductDTO productDTO);

    void updateProduct(Long id, ProductDTO productDTO);

    void applyDiscount(String category, Double discount);

}
