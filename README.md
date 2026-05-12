## Savings Goal API

A RESTful backend API built with Micronaut and PostgreSQL for creating and managing savings goals.

This project was built to strengthen backend engineering skills around API development, database persistence, validation, testing, and clean layered architecture.

## Tech Stack
- Java 17
- Micronaut
- PostgreSQL
- Docker
- Flyway
- Gradle
- JUnit 5

## Features

- Create savings goals
- Retrieve all goals
- Retrieve goal by ID
- Update existing goals
- Delete goals
- Validation for invalid requests
- Global exception handling
- Pagination support
- Integration testing
- DTO and mapper architecture
- Interactive Swagger/OpenAPI documentation
 
## Project Structure

- src/main/java/com/kenny/savingsgoal 
- ├── controller
- ├── service
- ├── repository
- ├── model
- ├── dto
- ├── mapper
- ├── exception

## Running the Application

1. Start PostgreSQL with Docker

- docker compose up -d

2. Run the application

- ./gradlew run
- Application runs on: http://localhost:8080

## Swagger UI

Once the application is running, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui
```
## Database

- PostgreSQL is used as the persistence layer.

- Flyway migrations are used for database schema versioning.

- Example table: savings_goal

## Endpoints 

| Method | Endpoint         | Description           |
| ------ | ---------------- | --------------------- |
| POST   | `/v1/goals`      | Create a savings goal |
| GET    | `/v1/goals`      | Retrieve all goals    |
| GET    | `/v1/goals/{id}` | Retrieve goal by ID   |
| PUT    | `/v1/goals/{id}` | Update goal           |
| DELETE | `/v1/goals/{id}` | Delete goal           |

## Example Request 
Request
- Create Goal:  POST /v1/goals
-  Content-Type: application/json

JSON

    {

    "name": "Emergency Fund",

    "targetAmount": 10000.00,

    "currentAmount": 500.00
    }

## Example Response
JSON

    {
    "id": 1,
    "name": "Emergency Fund",
    "targetAmount": 10000.00,
    "currentAmount": 500.00,
    "createdAt": "2026-04-18T20:01:40.926647"
    }

## Swagger Documentation

Interactive API documentation is available through Swagger UI.

After running the application, access: http://localhost:8080/swagger-ui

Swagger allows:

- viewing available endpoints
- testing requests directly from the browser
- inspecting request/response models

## Validation 

The API validates incoming requests using Jakarta Validation.

Examples:

- Name cannot be blank
- Target amount must be greater than 0
- Current amount cannot be negative

Invalid request returns: 400 Bad Request

## Error Handling

Custom exception handling is implemented using a global exception handler.

Example response:

    "status": 404,
    {
    "message": "Goal not found with id: 999"
    }

## Pagination

The API supports pagination for retrieving goals.

Example: GET /v1/goals?page=0&size=5

##Testing 

Integration tests were implemented using Micronaut Test and JUnit 5.

Test coverage includes:

- Successful creation
- Validation failures
- Resource not found scenarios
- Updating goals
- Deleting goals

Runs tests wih: ./gradlew test

## Future Improvements

- JWT authentication
-  CI/CD pipeline
-  Dockerising the application
-  Deployment to cloud infrastructure
-  Advanced filtering and sorting

## Author
Kehinde Abiola

## Micronaut 4.10.11 Documentation

- [User Guide](https://docs.micronaut.io/4.10.11/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.10.11/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.10.11/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://gradleup.com/shadow/)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
## Feature serialization-jackson documentation


- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature flyway documentation


- [Micronaut Flyway Database Migration documentation](https://micronaut-projects.github.io/micronaut-flyway/latest/guide/index.html)


- [https://flywaydb.org/](https://flywaydb.org/)


## Feature micronaut-aot documentation


- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature jdbc-hikari documentation


- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)


## Feature validation documentation


- [Micronaut Validation documentation](https://micronaut-projects.github.io/micronaut-validation/latest/guide/)


## Feature data-jdbc documentation


- [Micronaut Data JDBC documentation](https://micronaut-projects.github.io/micronaut-data/latest/guide/index.html#jdbc)


## Feature test-resources documentation


- [Micronaut Test Resources documentation](https://micronaut-projects.github.io/micronaut-test-resources/latest/guide/)


