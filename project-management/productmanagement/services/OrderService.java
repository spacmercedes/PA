package com.example.productmanagement.services;

import com.example.productmanagement.dtos.OrderDTO;
import com.example.productmanagement.dtos.StatisticsDTO;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getAllOrdersForCustomerId(Long id);

    DoubleSummaryStatistics getStatistics();

    DoubleSummaryStatistics getStatisticsByStatus(String status);

    DoubleSummaryStatistics getStatisticsByDate(LocalDate date);

}
