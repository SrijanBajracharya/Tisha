# Tisha - Jewelry Inventory Application

## Overview

Tisha is a Spring Boot-based application designed for managing jewelry inventory. It is built using Java 21 and employs various modern libraries and tools for development, testing, and deployment. This guide provides instructions to set up, build, test, and run the application with Docker support.

---

## Features

- **Spring Boot 3.4.0**: For rapid development.
- **Java 21**: Leveraging the latest Java features.
- **Flyway DB**: For database migrations.
- **Testcontainers**: Simplifying integration testing.
- **Spring Security**: For authentication and authorization.
- **OpenAPI**: For API documentation.
- **Docker**: Simplified deployment and containerization.

---

## Prerequisites

Ensure you have the following installed on your system:

- **Java 21**
- **Maven 3.9+**
- **Docker**

---

## Getting Started

### Clone the Repository
```bash
git clone <repository-url>
cd tisha
```

### Build the Application

Compile and package the application:
```bash
mvn clean install
```

### Running the Application

Start the application:
```bash
mvn spring-boot:run
```

### Access the Application

Once running, the application is available at:
- **Web**: `http://localhost:8080`
- **API Docs (Swagger UI)**: `http://localhost:8080/swagger-ui.html`

---

## Docker Support

### Build Docker Image

A `Dockerfile` is included for containerization. Build the image with:
```bash
docker build -t tisha:latest .
```

### Run Docker Container

Run the container with:
```bash
docker-compose up
```

Access the application on:
- **http://localhost:8080**

---

## Database Setup

This project uses Flyway for managing database migrations. Update the `application.yaml` file for database configurations (e.g., MySQL/MariaDB).

Default settings:
```yaml
spring.datasource.primary.jdbc-url: jdbc:mysql://localhost:3306/tisha_db
spring.datasource.primary.username: <YOUR_USERNAME>
spring.datasource.primary.password: <YOUR_PASSWORD>
```

---

## Testing

Run unit and integration tests using:
```bash
mvn test
```

For test containers:
```bash
mvn verify
```

---

## Key Dependencies

- **Spring Boot Starter**: Core features.
- **Spring Security**: For securing the application.
- **Flyway DB**: For versioned migrations.
- **Hibernate**: For ORM and database interactions.
- **Testcontainers**: For integration tests with MariaDB.

---

## Contributing

1. Fork the repository.
2. Create a new branch (`feature/your-feature-name`).
3. Commit your changes.
4. Push the branch.
5. Open a Pull Request.

---



