package com.example.productmanagement.services;

import com.example.productmanagement.dtos.CustomerDTO;
import com.example.productmanagement.entities.CustomerEntity;
import com.example.productmanagement.exceptions.CustomerNotFoundException;
import com.example.productmanagement.exceptions.IllegalDeleteException;
import com.example.productmanagement.exceptions.UserAlreadyExistsException;
import com.example.productmanagement.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.channels.UnresolvedAddressException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper mapper;

    @Autowired
    OrderService orderService;

    @Override
    public void addNewCustomer(CustomerDTO customerDTO) {
        var customer = customerRepo.findByName(customerDTO.getName());
        if(customer.isPresent())
            throw new UserAlreadyExistsException("Customer already exists");
        customerRepo.save(mapper.map(customerDTO, CustomerEntity.class));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        var entities = customerRepo.findAll();
        List<CustomerDTO> response = new LinkedList<>();
        entities.forEach(customer -> response.add(mapper.map(customer, CustomerDTO.class)));
        return response;
    }

    @Override
    public void modifyCustomer(Long id, CustomerDTO customerDTO) {
        var customer = customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException("No such customer for given ID"));
        customer.setName(customerDTO.getName());
        customer.setTier(customerDTO.getTier());
        customerRepo.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        var customer = customerRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("No such customer for given id"));
        if(orderService.getAllOrdersForCustomerId(id).size() == 0) {
            customerRepo.delete(customer);
        } else throw new IllegalDeleteException("Cannot delete customers who already made orders");
    }

}
