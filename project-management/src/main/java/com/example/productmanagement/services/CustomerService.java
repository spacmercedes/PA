package com.example.productmanagement.services;

import com.example.productmanagement.dtos.CustomerDTO;

import java.util.List;
//interfata cu metodele care urmeaza a fi implementate in CustomerServiceImpl
public interface CustomerService {
    void addNewCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    void modifyCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

}
