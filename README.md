# Spring Boot Security Starter – Basic Authentication

This project demonstrates how to build a secure Spring Boot application using **Spring Security** with custom login and registration handling — up to the point of securing endpoints using basic authentication and user roles (JWT not implemented yet).

It provides a modular and clean folder structure, making it easy to maintain, extend, and eventually transition into JWT-based stateless security.

---

## 📦 Folder Structure

springjwt/

├── config/ → Security & application configuration

│ ├── AppConfig

│ └── SecurityConfig

│
├── controller/ → REST controllers (Auth API)

│ └── AuthController

│
├── dto/ → Request & response payloads

│ ├── LoginRequest

│ ├── RegisterRequest

│ └── AuthResponse

│
├── entity/ → User JPA entity

│ └── User

│
├── repository/ → Spring Data JPA Repositories

│ └── UserRepo

│
├── service/ → Business logic layer

│ └── AuthService

│
├── health/ → Simple HealthCheck controller

│ └── HealthCheck

│
└── RushikeshApplication.java


---

## 🔐 What’s Implemented So Far

✅ Custom login and registration API  
✅ `User` entity with basic fields (`username`, `email`, `password`, `role`)  
✅ Role-based security using `Spring Security`  
✅ Password encryption with `BCryptPasswordEncoder`  
✅ Secured endpoints using `SecurityConfig`  
✅ Stateless security config (JWT planned)  
✅ Modular and DTO-based request/response structure  
✅ Health check endpoint (`/health/status`) for testing

---

## ⚙️ Technologies Used

- Java 21
- Spring Boot 3.5.x
- Spring Security
- Spring Data JPA
- MySQLPOST /auth/register
Body:
{
"username": "rushikesh",
"email": "rush@gmail.com",
"password": "password"
}


### Login User

POST /auth/login
Body:
{
"email": "rush@gmail.com",
"password": "password"
}


📝 (Currently returns mock `AuthResponse`; JWT will be added later in jwt2.)

---

## 📑 Configuration Highlight (`SecurityConfig.java`)

- `/auth/**` and `/health/**` are public
- All other endpoints are protected
- Stateless session config ready for JWT integration
- Password encoding configured via `BCryptPasswordEncoder`
- Custom `UserDetailsService` from `AuthService`

---

## 🚀 How to Run

1. Clone this repo
2. Set up MySQL (or switch to H2 for dev)
3. Update `application.properties`
4. Build & Run:
```bash
mvn clean install
mvn spring-boot:run
- Maven

---

## 📡 API Overview

### Register User


