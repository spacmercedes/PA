package com.example.productmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
    Long count;
    Double average;
    Double max;
    Double min;
    Double sum;
}
