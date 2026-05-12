package com.kenny.savingsgoal.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

import java.util.Map;

@Produces
@Singleton
public class GlobalExceptionHandler implements ExceptionHandler<GoalNotFoundException, HttpResponse<Map<String, Object>>> {

    @Override
    public HttpResponse<Map<String, Object>> handle(HttpRequest request, GoalNotFoundException exception) {
        return HttpResponse.notFound(
                Map.of(
                        "message", exception.getMessage(),
                        "status", 404
                )
        );
    }
}