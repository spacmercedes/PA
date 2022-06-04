package com.example.productmanagement.services;

import com.example.productmanagement.dtos.ProductDTO;
import com.example.productmanagement.dtos.StatisticsDTO;
import com.example.productmanagement.entities.ProductEntity;
import com.example.productmanagement.exceptions.ProductNotFoundException;
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
    public DoubleSummaryStatistics getStatistics() {
        return productRepo.findAll()
                .stream()
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();
    }

    @Override
    public void addNewProduct(ProductDTO productDTO) {
        productRepo.save(mapper.map(productDTO, ProductEntity.class));
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        var product = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("No such product for given id"));
        product.setName(productDTO.getName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        productRepo.save(product);
    }

    @Override
    public void applyDiscount(String category, Double discount) {
        var productList = productRepo.findAll();
        var updatedProductList = productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .map(product -> product.withPrice(product.getPrice() * (1 - discount/100)))
                .collect(Collectors.toList());
        productRepo.saveAll(updatedProductList);
    }
}
