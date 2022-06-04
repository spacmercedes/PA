package com.example.productmanagement.services;

import com.example.productmanagement.dtos.CustomerDTO;
import com.example.productmanagement.dtos.OrderDTO;
import com.example.productmanagement.dtos.ProductDTO;
import com.example.productmanagement.dtos.StatisticsDTO;
import com.example.productmanagement.entities.OrderEntity;
import com.example.productmanagement.entities.ProductEntity;
import com.example.productmanagement.repo.OrderRepo;
import org.hibernate.stat.Statistics;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ModelMapper mapper;
// nu putem afisa direct cu findAll sau sa il mapam , dar cumva se autoreferentiaza intre ele si o sa am ca si return un JSON
    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> resultList = new LinkedList<>();
        List<OrderEntity> orderEntityList = orderRepo.findAll(); // luam toata lista de entitati si vrem sa o mapam la DTO
        orderEntityList.forEach(
                orderEntity -> resultList.add( //lista de return
                        OrderDTO.builder() //adaugam cu builder
                                .id(orderEntity.getId()) //si pun id-ul la entitate
                                .orderDate(orderEntity.getOrderDate()) //orderDate il ia de la entitate
                                .deliveryDate(orderEntity.getDeliveryDate()) //delivery il ia de la entitate
                                .customer(mapper.map(orderEntity.getCustomer(), CustomerDTO.class)) //pentru cusotmer fac mapare la DTO
                                .status(orderEntity.getStatus())
                                .products( //la product am o lista de ProductEntity si trebuie sa o transformm intro lista de porductDTO
                                        orderEntity.getProducts().stream()  //pentru asta ia produsele si le pune intr-un stream
                                                .map(productEntity -> mapper.map(productEntity, ProductDTO.class)) //dupa pentru fiecare entitate o mapez intr-o DTO
                                                .collect(Collectors.toList()) //si il pun intr-o noua lista
                                )  //in final produsele astea vor fi o lista de porductDTO
                                .build()
                )
        );
        return resultList;
    }

    @Override
    public List<OrderDTO> getAllOrdersForCustomerId(Long id) { //aplic metoda din micorserviciu getAllOrders
        return getAllOrders().stream() //iau obiectele, le pun in stream
                .filter(order -> order.getCustomer().getId().equals(id)) //le filtrez , dupa pentru fiecare comanda ia acele comenzi pentru care customer-ul are id-ul respectiv si le pun intr-o lista
                .collect(Collectors.toList());
    }


    @Override //fac mapare dupa produs si fac statisticile cu summaryStatistics(metoda de la streamuri)
    public DoubleSummaryStatistics getStatistics() {
        return orderRepo.findAll().stream()
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();

    }

    @Override
    public DoubleSummaryStatistics getStatisticsByStatus(String status) {
        return orderRepo.findAll().stream()
                .filter(o -> o.getStatus().equalsIgnoreCase(status))
                .flatMap(order -> order.getProducts().stream())//flatMap cumva schimba tipul stream-ului , din stream<orderEntity> in <ProductEntity> deoarece am mapat dupa stream-ul de produse
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();
    }

    @Override //prima zi din luna curenta pana la prima zi din luna urmatoare
    public DoubleSummaryStatistics getStatisticsByDate(LocalDate date) {
        return orderRepo.findAll().stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(date.getYear(), date.getMonth(), 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(date.getYear(), date.getMonth().plus(1), 1)) < 0)
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();
    }

}
