package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.ProductDTO;
import com.example.productmanagement.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getAllProductsByCategory(category));
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody ProductDTO productDTO) {
        productService.addNewProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestParam Long id, @RequestBody ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/discount")
    public ResponseEntity<?> applyDiscount(@RequestParam (required = false) String category,
                                           @RequestParam Double discount) {
        productService.applyDiscount(category, discount);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
