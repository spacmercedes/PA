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

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> resultList = new LinkedList<>();
        List<OrderEntity> orderEntityList = orderRepo.findAll();
        orderEntityList.forEach(
                orderEntity -> resultList.add(
                        OrderDTO.builder()
                                .id(orderEntity.getId())
                                .orderDate(orderEntity.getOrderDate())
                                .deliveryDate(orderEntity.getDeliveryDate())
                                .customer(mapper.map(orderEntity.getCustomer(), CustomerDTO.class))
                                .status(orderEntity.getStatus())
                                .products(
                                        orderEntity.getProducts().stream()
                                                .map(productEntity -> mapper.map(productEntity, ProductDTO.class))
                                                .collect(Collectors.toList())
                                )
                                .build()
                )
        );
        return resultList;
    }

    @Override
    public List<OrderDTO> getAllOrdersForCustomerId(Long id) {
        return getAllOrders().stream()
                .filter(order -> order.getCustomer().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
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
                .flatMap(order -> order.getProducts().stream())
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();
    }

    @Override
    public DoubleSummaryStatistics getStatisticsByDate(LocalDate date) {
        return orderRepo.findAll().stream()
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(date.getYear(), date.getMonth(), 1)) >= 0)
                .filter(o -> o.getOrderDate().compareTo(LocalDate.of(date.getYear(), date.getMonth().plus(1), 1)) < 0)
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(ProductEntity::getPrice)
                .summaryStatistics();
    }

}
