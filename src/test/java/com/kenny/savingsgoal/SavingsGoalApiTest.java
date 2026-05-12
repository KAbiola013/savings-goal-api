package com.kenny.savingsgoal;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.micronaut.http.client.exceptions.HttpClientResponseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.kenny.savingsgoal.dto.SavingsGoalResponse;


@MicronautTest
class SavingsGoalApiTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void shouldCreateSavingsGoal() {
        String requestBody = """
            {
              "name": "Test Goal",
              "targetAmount": 1000.00,
              "currentAmount": 100.00
            }
            """;

        HttpRequest<String> request = HttpRequest.POST("/v1/goals", requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        HttpResponse<String> response = client.toBlocking().exchange(request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatus());

        assertTrue(response.body().contains("Test Goal"));
        assertTrue(response.body().contains("1000.00"));
    }

    @Test
    void shouldRejectInvalidSavingsGoal() {
        String requestBody = """
            {
              "name": "",
              "targetAmount": 0,
              "currentAmount": -1
            }
            """;

        HttpRequest<String> request = HttpRequest.POST("/v1/goals", requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        HttpClientResponseException exception = assertThrows(
                HttpClientResponseException.class,
                () -> client.toBlocking().exchange(request, String.class)
        );

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    void shouldReturnNotFoundWhenGoalDoesNotExist() {
        HttpRequest<String> request = HttpRequest.GET("/v1/goals/999");

        HttpClientResponseException exception = assertThrows(
                HttpClientResponseException.class,
                () -> client.toBlocking().exchange(request, String.class)
        );

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        System.out.println(exception.getResponse().getBody(String.class).orElse("NO BODY"));
    }

    @Test
    void shouldUpdateSavingsGoal() {
        String createRequest = """
            {
              "name": "Original Goal",
              "targetAmount": 1000.00,
              "currentAmount": 100.00
            }
            """;

        HttpRequest<String> createHttpRequest =
                HttpRequest.POST("/v1/goals", createRequest)
                        .contentType(MediaType.APPLICATION_JSON);

        HttpResponse<SavingsGoalResponse> createResponse =
                client.toBlocking().exchange(createHttpRequest, SavingsGoalResponse.class);

        Integer createdId = createResponse.body().getId();

        String updateRequest = """
            {
              "name": "Updated Goal",
              "targetAmount": 2000.00,
              "currentAmount": 500.00
            }
            """;

        HttpRequest<String> updateHttpRequest =
                HttpRequest.PUT("/v1/goals/" + createdId, updateRequest)
                        .contentType(MediaType.APPLICATION_JSON);

        HttpResponse<String> updateResponse =
                client.toBlocking().exchange(updateHttpRequest, String.class);

        assertEquals(HttpStatus.OK, updateResponse.getStatus());
        assertTrue(updateResponse.body().contains("Updated Goal"));
    }

    @Test
    void shouldDeleteSavingsGoal() {
        String requestBody = """
            {
              "name": "Delete Goal",
              "targetAmount": 1000.00,
              "currentAmount": 100.00
            }
            """;

        HttpRequest<String> createRequest =
                HttpRequest.POST("/v1/goals", requestBody)
                        .contentType(MediaType.APPLICATION_JSON);

        HttpResponse<SavingsGoalResponse> createResponse =
                client.toBlocking().exchange(createRequest, SavingsGoalResponse.class);

        Integer createdId = createResponse.body().getId();

        HttpRequest<String> deleteRequest =
                HttpRequest.DELETE("/v1/goals/" + createdId);

        HttpResponse<String> deleteResponse =
                client.toBlocking().exchange(deleteRequest, String.class);

        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatus());
    }
}