# 🚗 RideShare Backend — Spring Boot + MongoDB + JWT

A simplified ride-sharing backend application built with **Spring Boot 3**, **MongoDB**, **JWT Authentication**, **Role-Based Access**, and **Global Exception Handling** — with **no Lombok**.

---

## 📌 Features

### 🔐 Authentication & Authorization
- Register as **User** or **Driver**
- Login with JWT token generation
- BCrypt password hashing
- JWT filter for secured endpoints
- Role-based access (ROLE_USER / ROLE_DRIVER)

### 🚘 Ride Functionality
- Users can request rides
- Drivers can view pending rides
- Drivers can accept rides
- Users or Drivers can complete rides

### ⚙️ Architecture Highlights
- DTO-based API communication
- MongoDB document models
- Clean service + controller separation
- Centralized GlobalExceptionHandler
- Custom exceptions

---

## 🏗️ Tech Stack

| Layer       | Technology               |
|-------------|---------------------------|
| Backend     | Spring Boot 3 (Java 17+) |
| Database    | MongoDB                  |
| Security    | Spring Security 6 + JWT  |
| Build Tool  | Maven                    |

---

## 📂 Project Structure

