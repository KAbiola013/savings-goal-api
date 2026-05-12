package com.kenny.savingsgoal.controller;

import com.kenny.savingsgoal.dto.CreateSavingsGoalRequest;
import com.kenny.savingsgoal.dto.SavingsGoalResponse;
import com.kenny.savingsgoal.model.SavingsGoal;
import com.kenny.savingsgoal.service.SavingsGoalService;
import com.kenny.savingsgoal.mapper.SavingsGoalMapper;
import com.kenny.savingsgoal.dto.UpdateSavingsGoalRequest;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.Valid;
import java.util.List;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;

@Validated
@Controller("/v1/goals")
public class SavingsGoalController {

    private final SavingsGoalService service;

    public SavingsGoalController(SavingsGoalService service) {
        this.service = service;
    }

    @Get
    public List<SavingsGoalResponse> getAllGoals(Pageable pageable) {
        Page<SavingsGoal> page = service.getAllGoals(pageable);

        return page.getContent()
                .stream()
                .map(SavingsGoalMapper::toResponse)
                .toList();
    }

    @Post
    @Status(HttpStatus.CREATED)
    public SavingsGoalResponse createGoal(@Body @Valid CreateSavingsGoalRequest request) {
        SavingsGoal goal = SavingsGoalMapper.toEntity(request);
        SavingsGoal saved = service.createGoal(goal);
        return SavingsGoalMapper.toResponse(saved);

    }

    @Get("/{id}")
    public SavingsGoalResponse getGoalById(@PathVariable Integer id) {
        return SavingsGoalMapper.toResponse(service.getGoalById(id));
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public void deleteGoalById(@PathVariable Integer id) {
        service.deleteGoalById(id);
    }

    @Put("/{id}")
    public SavingsGoalResponse updateGoal(
            @PathVariable Integer id,
            @Body @Valid UpdateSavingsGoalRequest request) {
        SavingsGoal updated = SavingsGoalMapper.toEntity(request);
        SavingsGoal saved = service.updateGoal(id, updated);
        return SavingsGoalMapper.toResponse(saved);
    }
}