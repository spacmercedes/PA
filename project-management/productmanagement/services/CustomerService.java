package com.example.productmanagement.services;

import com.example.productmanagement.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addNewCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    void modifyCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

}
