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
        var products = productRepo.findAll()//din toata lsita de entitati am pus-o intr-un stream de date
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category)) // am aplicat un filter: pentru fiecare produs , vezi ca categoria lui sa fie egala cu cea data de parametru
                .toList(); //apoi pun stream-ul intr-o lista

        List<ProductDTO> resultList = new LinkedList<>();
        products.forEach(entity -> resultList.add(mapper.map(entity, ProductDTO.class))); //mapare la DTO
        return resultList;
    }

    @Override
    public StatisticsDTO getStatistics() {
        DoubleSummaryStatistics statistics = productRepo.findAll()
                .stream()           //productEntity :: getPrice -  se refera la meoda getPrice din clasa ProductEntity
                .mapToDouble(ProductEntity::getPrice)//mapToDouble returneaza un stream de double-uri , in functie de o annumita metoda din clasele din care fac parte elementele stream-ului
                .summaryStatistics();

        return StatisticsDTO.builder() //fac statistici pe min , max , average
                .sum(statistics.getSum())
                .average(statistics.getAverage())
                .count(statistics.getCount())
                .max(statistics.getMax())
                .min(statistics.getMin())
                .build();
    }
}
