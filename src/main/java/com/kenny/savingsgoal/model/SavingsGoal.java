package com.kenny.savingsgoal.model;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedEntity("savings_goal")
@Serdeable
@Data
public class SavingsGoal {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String name;

    @MappedProperty("target_amount")
    @DecimalMin("0.01")
    private BigDecimal targetAmount;

    @MappedProperty("current_amount")
    @DecimalMin("0.00")
    private BigDecimal currentAmount;

    @MappedProperty("created_at")
    @DateCreated
    private LocalDateTime createdAt;
}