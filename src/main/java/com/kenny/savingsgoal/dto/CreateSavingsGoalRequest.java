package com.kenny.savingsgoal.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Serdeable
@Data
public class CreateSavingsGoalRequest {

    @NotBlank
    private String name;

    @DecimalMin("0.01")
    private BigDecimal targetAmount;

    @DecimalMin("0.00")
    private BigDecimal currentAmount;
}