package com.kenny.savingsgoal.exception;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UnexpectedExceptionHandlerTest {

    private final UnexpectedExceptionHandler handler = new UnexpectedExceptionHandler();

    @Test
    void shouldReturnGenericMessageWithoutInternalDetails() {
        String internalDetail = "ERROR: value too long for type character varying(255)";

        HttpResponse<Map<String, Object>> response =
                handler.handle(HttpRequest.GET("/v1/goals"), new RuntimeException(internalDetail));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatus());
        assertFalse(response.body().toString().contains(internalDetail));
    }
}