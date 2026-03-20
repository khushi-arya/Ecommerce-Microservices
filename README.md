                Client (UI / Postman)
                        │
                        ▼
                 API Gateway
           (JWT + Spring Security)
                        │
                        ▼
                  Eureka Server
                        │
    -------------------------------------------------
    | Users | Products | Cart | Orders | Inventory |
    | Payment |
    -------------------------------------------------


# Ecommerce Microservices Platform

A production-style e-commerce backend built with Java 8, Spring Boot, and microservices architecture.

## Architecture Overview

The system is decomposed into 7 independent services:

| Service | Responsibility |
|---|---|
| API Gateway | Centralized routing, load balancing, authentication |
| User Service | User registration, login, profile management |
| Product Service | Product catalog, search, and details |
| Order Service | Order creation, tracking, and lifecycle |
| Cart Service | Cart operations and item management |
| Inventory Service | Stock tracking and availability checks |
| Payment Service | Payment processing and transaction flow |

## Tech Stack

- **Language**: Java 8
- **Framework**: Spring Boot
- **Service Discovery**: Eureka (Netflix OSS)
- **API Gateway**: Spring Cloud Gateway
- **Security**: Spring Security (JWT)
- **Database**: MySQL (database-per-service pattern)
- **Containerization**: Docker
- **Build Tool**: Maven

## How to Run

```bash
# Clone the repo
git clone https://github.com/khushi-arya/Ecommerce-Microservices.git

# Start each service (run in order)
cd APIgateway && mvn spring-boot:run
cd UserService && mvn spring-boot:run
# ... repeat for each service
```

## Key Design Decisions

- **Database-per-service**: Each microservice owns its schema, avoiding tight coupling
- **Centralized auth**: API Gateway handles authentication so individual services stay stateless
- **Service discovery**: Eureka eliminates hard-coded URLs between services
- **Containerized**: Docker ensures consistent environments across dev and production
