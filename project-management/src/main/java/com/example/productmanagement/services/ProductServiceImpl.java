package com.example.productmanagement.services;

import com.example.productmanagement.dtos.ProductDTO;
import com.example.productmanagement.dtos.StatisticsDTO;
import com.example.productmanagement.entities.ProductEntity;
import com.example.productmanagement.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        var products = productRepo.findAll();
        List<ProductDTO> resultList = new LinkedList<>();
        products.forEach(entity -> resultList.add(mapper.map(entity, ProductDTO.class)));
        return resultList;
    }

    @Override
    public List<ProductDTO> getAllProductsByCategory(String category) {
        var products = productRepo.findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();

        List<ProductDTO> resultList = new LinkedList<>();
        products.forEach(entity -> resultList.add(mapper.map(entity, ProductDTO.class)));
        return resultList;
    }

    @Override
    public StatisticsDTO getStatistics() {
        DoubleSummaryStatistics statistics = productRepo.findAll()
                .stream()
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();

        return StatisticsDTO.builder()
                .sum(statistics.getSum())
                .average(statistics.getAverage())
                .count(statistics.getCount())
                .max(statistics.getMax())
                .min(statistics.getMin())
                .build();
    }
}
