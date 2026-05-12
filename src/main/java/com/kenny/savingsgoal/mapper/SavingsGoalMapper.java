package com.kenny.savingsgoal.mapper;

import com.kenny.savingsgoal.dto.CreateSavingsGoalRequest;
import com.kenny.savingsgoal.dto.SavingsGoalResponse;
import com.kenny.savingsgoal.model.SavingsGoal;
import com.kenny.savingsgoal.dto.UpdateSavingsGoalRequest;

public class SavingsGoalMapper {

    public static SavingsGoal toEntity(CreateSavingsGoalRequest request) {
        SavingsGoal goal = new SavingsGoal();
        goal.setName(request.getName());
        goal.setTargetAmount(request.getTargetAmount());
        goal.setCurrentAmount(request.getCurrentAmount());
        return goal;
    }

    public static SavingsGoalResponse toResponse(SavingsGoal goal) {
        SavingsGoalResponse response = new SavingsGoalResponse();
        response.setId(goal.getId());
        response.setName(goal.getName());
        response.setTargetAmount(goal.getTargetAmount());
        response.setCurrentAmount(goal.getCurrentAmount());
        response.setCreatedAt(goal.getCreatedAt());
        return response;
    }

    public static SavingsGoal toEntity(UpdateSavingsGoalRequest request) {
        SavingsGoal goal = new SavingsGoal();
        goal.setName(request.getName());
        goal.setTargetAmount(request.getTargetAmount());
        goal.setCurrentAmount(request.getCurrentAmount());
        return goal;
    }
}