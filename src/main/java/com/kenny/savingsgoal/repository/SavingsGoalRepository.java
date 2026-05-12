package com.kenny.savingsgoal.repository;

import com.kenny.savingsgoal.model.SavingsGoal;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;


@Repository
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface SavingsGoalRepository extends CrudRepository<SavingsGoal, Integer> {

    Page<SavingsGoal> findAll(Pageable pageable);
}