package com.example.productmanagement.services;

import com.example.productmanagement.dtos.CustomerDTO;
import com.example.productmanagement.entities.CustomerEntity;
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
//clasa in care se face implementarea metodelor din interfata
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired //se injeccteaza repositoriul CostumerRepo
    CustomerRepo customerRepo;

    @Autowired //se injecteaza mapperul care va ajuta la mapare
    ModelMapper mapper;

    @Autowired
    OrderService orderService;


    @Override
    public void addNewCustomer(CustomerDTO customerDTO) {
        var customer = customerRepo.findByName(customerDTO.getName());
        if(customer.isPresent()) //verifica daca exista un customer cu acelasi nume,daca este
            throw new UserAlreadyExistsException("Customer already exists");//se arunca o exceptie care e tratata in ExceptionHandlerAdvice
        customerRepo.save(mapper.map(customerDTO, CustomerEntity.class)); //daca nu exista, se salveaza entitatea (mapeaza de la DTO la entity)
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        var entities = customerRepo.findAll(); //mi se returneaza o lista de entitati, dar nu o afiseaza , deoarece pot fi informatii senzitive in baza de date, si le transformam la DTO
        List<CustomerDTO> response = new LinkedList<>(); //creez o lista noua
        entities.forEach(customer -> response.add(mapper.map(customer, CustomerDTO.class))); // pentru fiecare lista din entitate am facut un foreach ,dau push la lista respectiva si aduni lista
        return response;
    }

    @Override //update customer - se faace direct pe entitate, deoarece daca le faci pe DTO  si dupa aia salvezi xu mapper , practic se creeaza o noua entiatte si are alt id si o sa obtin entitati care reprezinta acelasi lucru
    public void modifyCustomer(Long id, CustomerDTO customerDTO) {
        var customer = customerRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("No such customer for given ID")); //functia lambda creeaza o exceptie si o arunca mai departe
        customer.setName(customerDTO.getName()); //daca e succes, se modifica customer
        customer.setTier(customerDTO.getTier());
        customerRepo.save(customer);  //si se salveaza in repository
    }

    @Override
    public void deleteCustomer(Long id) { //daca customer e implicat in alte comenzi, nu poate fi sters
        var customer = customerRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("No such customer for given id"));
        if(orderService.getAllOrdersForCustomerId(id).size() == 0) {
            customerRepo.delete(customer);
        } else throw new IllegalDeleteException("Cannot delete customers who already made orders");
    }

}
