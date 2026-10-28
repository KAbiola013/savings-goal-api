# Savings Goal API

[![CI](https://github.com/KAbiola013/savings-goal-api/actions/workflows/ci.yml/badge.svg)](https://github.com/KAbiola013/savings-goal-api/actions/workflows/ci.yml)

A REST API for creating and managing savings goals, built with Java 17, Micronaut and PostgreSQL.

I built this to practise backend engineering outside work: layered architecture, input validation, database migrations, error handling and testing, with every change made through a pull request and checked by CI.

## Tech stack

- Java 17, Micronaut 4
- PostgreSQL 15, Flyway migrations, Micronaut Data JDBC
- JUnit 5, Micronaut Test, Testcontainers (via Micronaut Test Resources)
- Gradle, Docker Compose, GitHub Actions
- OpenAPI / Swagger UI

## Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/v1/goals` | Create a goal |
| GET | `/v1/goals?page=0&size=10` | List goals (paginated) |
| GET | `/v1/goals/{id}` | Get one goal |
| PUT | `/v1/goals/{id}` | Replace a goal |
| DELETE | `/v1/goals/{id}` | Delete a goal |

Interactive documentation is available at `http://localhost:8080/swagger-ui` while the app is running.

### Example

```http
POST /v1/goals
Content-Type: application/json

{
  "name": "Emergency Fund",
  "targetAmount": 10000.00,
  "currentAmount": 500.00
}
```

 Response (`201 Created`):
  
  ```json
  {
  "id": 1,
  "name": "Emergency Fund",
  "targetAmount": 10000.00,
  "currentAmount": 500.00,
  "createdAt": "2026-10-28T14:52:03.692"
}
```

## Validation

Requests are validated before they reach the service layer. Invalid requests return `400 Bad Request` with the field that failed.

| Field | Rules |
|---|---|
| `name` | required, not blank, maximum 255 characters |
| `targetAmount` | required, at least 0.01 |
| `currentAmount` | required, at least 0.00 (zero is allowed; a missing value is not) |

`PUT` replaces the whole goal, so the same rules apply to updates.

## Error handling

| Situation | Status | Response |
|---|---|---|
| Validation failure | 400 | which field failed and why, e.g. `request.targetAmount: must not be null` |
| Goal not found | 404 | `{"message": "Goal not found with id: 999", "status": 404}` |
| Anything unexpected | 500 | `{"message": "An unexpected error occurred", "status": 500}` |

Unexpected errors are logged in full on the server (with the request method and path), but the client only receives a generic message. This avoids leaking database or framework details in error responses.

## Running locally

Requirements: Java 17 and Docker.

1. Start PostgreSQL:

   ```bash
   docker compose up -d
   ```

2. Run the app (uses the `dev` environment and the Compose database):

   ```bash
   ./gradlew run
   ```

   The API runs on `http://localhost:8080`.

### Configuration

Database settings are read from environment variables, with local defaults for development:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/savings_goals` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | `postgres` |

Docker Compose reads `POSTGRES_DB`, `POSTGRES_USER` and `POSTGRES_PASSWORD` in the same way.

## Testing

```bash
./gradlew test
```

The tests only need Docker. They do not use the development database: Micronaut Test Resources starts a throwaway `postgres:15` container for each run, so every run starts from a clean state and leaves nothing behind.

The suite includes:

- **Integration tests** that call the API over HTTP against a real PostgreSQL database, covering successful requests, validation failures (including boundary cases such as a 255 vs 256 character name) and not-found responses
- **A unit test** for the catch-all exception handler, verified by deliberately breaking the handler and confirming the test fails

GitHub Actions runs the full suite on every pull request and every push to `main`.

**Troubleshooting:** on Docker Engine 29 or later, Testcontainers may fail to connect with a `400` error. Setting the Docker API version on your machine fixes it:

```bash
echo "api.version=1.44" > ~/.docker-java.properties
```

## Project structure

```
src/main/java/com/kenny/savingsgoal
├── controller   HTTP endpoints
├── dto          request and response objects, with validation rules
├── mapper       converts between DTOs and entities
├── service      business logic
├── repository   database access (Micronaut Data)
├── model        database entities
└── exception    exception types and handlers
```

## Possible improvements

- Return page metadata (total count, page number) from the list endpoint
- Business rules, such as contributions towards a goal and preventing `currentAmount` exceeding `targetAmount`
- Store `createdAt` with a time zone (`Instant` or `OffsetDateTime`)
- A `NOT NULL` database constraint on `current_amount` to back up the request validation
- Authentication