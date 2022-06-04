package com.example.productmanagement.controllers;

import com.example.productmanagement.dtos.CustomerDTO;
import com.example.productmanagement.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Expun end-point-urile la care se vor face requesturile de POST,GET,PUT,DELETE pentru Customers
@RequestMapping("/customers")
public class CustomerController {

    @Autowired //injectam serviciul, deoarece este best prcatice sa facem logica cu microservicii, nu in controlere
    CustomerService customerService;

    @PostMapping //adaugam un nou customer (POST - adaugam o noua resursa in baza de date)
    public ResponseEntity<?> addNewCustomer(@RequestBody CustomerDTO customerDTO) { //cer ca si parametru un DTO, pentru a respecta principiile SOLID si a nu lucra cu entitati  //@RequestBody - cere un JSON in body
        customerService.addNewCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build(); //returneaza un cod status (http comunica cu coduri de status) in acest caz CREATED, daca resursa a fost creata cu succes
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("/{id}") //modific o resursa care este identificata printr-un id, de-aceea am nevoie de un endpoint-variabil
    public ResponseEntity<?> modifyCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) { //@PathVariable - cere id variabil //cere la fel un CustomerDTO care contine customer modificat
        customerService.modifyCustomer(id, customerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); //daca nu returnam nici o resursa primim 204 NO_CONTENT
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}