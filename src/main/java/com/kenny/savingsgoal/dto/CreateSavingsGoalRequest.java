package com.kenny.savingsgoal.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Serdeable
@Data
public class CreateSavingsGoalRequest {

    @Size(max = 255)
    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal targetAmount;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal currentAmount;
}