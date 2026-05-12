package com.kenny.savingsgoal.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Serdeable
@Data
public class SavingsGoalResponse {

    private Integer id;
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDateTime createdAt;
}