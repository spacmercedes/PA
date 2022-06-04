package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.CustomerDTO;
import com.example.productmanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.addNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerService.modifyCustomer(id, customerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}