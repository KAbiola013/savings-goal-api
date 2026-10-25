package com.kenny.savingsgoal.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
@Produces
@Singleton
public class UnexpectedExceptionHandler implements ExceptionHandler<Exception, HttpResponse<Map<String, Object>>> {

    private static final Logger LOG = LoggerFactory.getLogger(UnexpectedExceptionHandler.class);


    @Override
    public HttpResponse<Map<String, Object>> handle(HttpRequest request, Exception exception) {

        LOG.error("Unexpected error handling {} {}", request.getMethod(), request.getPath(), exception);

        return HttpResponse.serverError(
                Map.of(
                        "message", "An unexpected error occurred",
                        "status", 500
                )
        );
    }
}