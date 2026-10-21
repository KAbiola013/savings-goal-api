package com.kenny.savingsgoal.dto;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Serdeable
@Data
public class UpdateSavingsGoalRequest {

    @NotBlank
    private String name;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal targetAmount;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal currentAmount;
}
