package com.kenny.savingsgoal.service;

import com.kenny.savingsgoal.exception.GoalNotFoundException;
import com.kenny.savingsgoal.model.SavingsGoal;
import com.kenny.savingsgoal.repository.SavingsGoalRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import jakarta.inject.Singleton;
import java.util.List;

    @Singleton
    public class SavingsGoalService {

        private final SavingsGoalRepository repository;

        public SavingsGoalService(SavingsGoalRepository repository) {
            this.repository = repository;
        }

        public Page<SavingsGoal> getAllGoals(Pageable pageable) {
            return repository.findAll(pageable);
        }

        public SavingsGoal getGoalById(Integer id) {
            return repository.findById(id).orElseThrow(
                    () -> new GoalNotFoundException("Goal not found with id: " + id));
        }

        public void deleteGoalById(Integer id) {
            SavingsGoal goal = repository.findById(id)
                    .orElseThrow(() -> new GoalNotFoundException("Goal not found with id: " + id));

            repository.delete(goal);
        }

        public List<SavingsGoal> getAllGoals() {
            return repository.findAll();
        }

        public SavingsGoal createGoal(SavingsGoal goal) {
            return repository.save(goal);
        }

        public SavingsGoal updateGoal(Integer id, SavingsGoal updatedGoal) {
            SavingsGoal existingGoal = repository.findById(id)
                    .orElseThrow(() -> new GoalNotFoundException("Goal not found with id: " + id));

            existingGoal.setName(updatedGoal.getName());
            existingGoal.setTargetAmount(updatedGoal.getTargetAmount());
            existingGoal.setCurrentAmount(updatedGoal.getCurrentAmount());

            return repository.update(existingGoal);
        }


    }


