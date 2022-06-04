package com.example.productmanagement.controllers;

import com.example.productmanagement.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> getStatistics(@RequestParam(required = false) String status, //pot sa apelez end-point-ul /statistics, si pot sa ii dau sau nu un parametru //daca am status, fa dupa status
                                           @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) { //daca am date - fa dupa date  //DateFormat - formatez data, pentru ca localDate avea minute
        if (status != null)
            return ResponseEntity.ok(orderService.getStatisticsByStatus(status));
        if (date != null)
            return ResponseEntity.ok(orderService.getStatisticsByDate(date));
        return ResponseEntity.ok(orderService.getStatistics());
    }

}
