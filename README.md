# 🍽️ PlateMate

> **A CRM platform for the hospitality industry** — manage waiters, shifts, holidays and payroll details from a single, clean Spring Boot backend.

<p align="left">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.5.x-6DB33F?logo=springboot&logoColor=white">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white">
  <img alt="Maven" src="https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white">
  <img alt="Status" src="https://img.shields.io/badge/Status-In%20Development-yellow">
</p>

---

## 📖 Overview

**PlateMate** is a backend service that helps restaurants, bars and hospitality venues coordinate their staff. It centralises the moving parts of workforce management — onboarding employees, assigning them to shifts, handling holiday requests, and keeping payroll bank details tidy — behind a clean REST API.

Built on **Spring Boot 3** and **PostgreSQL**, PlateMate favours a layered architecture (Controller → Service → Repository) with **MapStruct** for DTO mapping and **Lombok** to keep boilerplate to a minimum.

---

## ✨ Features

- 👤 **User Management** — register staff, update personal info, change passwords, and reassign roles.
- 🧑‍🍳 **Waiter Profiles** — extended user type with photo, availability (`isBusy` / `isOnHoliday`) and shift history.
- 🗓️ **Shift Tracking** — query a waiter's shifts, filter by approval status, or narrow down by date.
- 🌴 **Holiday Workflow** — create, approve, decline and delete holiday requests with a status lifecycle.
- 💳 **Bank Details** — securely associate payroll banking info with each user (one-to-one).
- 🔐 **Role-Based Model** — built-in roles for `WAITER`, `MANAGER` and `ADMIN`.
- 🧯 **Rich Exception Handling** — purpose-built exceptions for clean, predictable error responses.

---

## 🏗️ Architecture

PlateMate follows a classic layered Spring Boot design:

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│  Controller  │ ──► │   Service    │ ──► │  Repository  │ ──► │  PostgreSQL  │
│  (REST API)  │     │ (Business    │     │  (Spring     │     │  (Database)  │
│              │ ◄── │   Logic)     │ ◄── │  Data JPA)   │ ◄── │              │
└──────────────┘     └──────────────┘     └──────────────┘     └──────────────┘
        ▲                    ▲
        │                    │
      DTOs  ◄── MapStruct ── Entities
```

### 📂 Package layout

```
com.company
├── Controller     # REST endpoints
├── Service        # Business logic (User, Waiter, Holiday, Auth)
├── Repository     # Spring Data JPA repositories
├── Entity         # JPA entities (UserEntity, Waiter, Shift, ShiftOrder, Holiday, BankDetails)
├── DTO            # Request/response objects, grouped by domain (user, shift, holiday)
├── Mapper         # MapStruct entity ⇄ DTO mappers
├── Enums          # Role, ShiftStatus, HolidayStatus
└── Exceptions     # Custom domain exceptions
```

---

## 🧩 Domain Model

| Entity          | Description                                                                 |
|-----------------|-----------------------------------------------------------------------------|
| **UserEntity**  | Base staff member — name, email, phone, password, NIN, role, bank details. Uses `JOINED` inheritance. |
| **Waiter**      | Extends `UserEntity`; adds photo, availability flags and a list of shifts.   |
| **Shift**       | A waiter's assignment to a `ShiftOrder`, with a status and request/response timestamps. |
| **ShiftOrder**  | A staffing request — number of staff, pay rate, start/end time and description. |
| **Holiday**     | A time-off request with reason, duration, status and owning waiter.          |
| **BankDetails** | Payroll banking info (bank name, account number, sort code), one per user.   |

**Enums**

- `Role` → `WAITER`, `ADMIN`, `MANAGER`
- `ShiftStatus` → `APPROVED`, `PENDING`, `REJECTED`, `COMPLETED`
- `HolidayStatus` → `PENDING`, `APPROVED`, `DECLINED`

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Java 17+**
- **PostgreSQL 12+** (running locally)
- **Maven** (or use the bundled `./mvnw` wrapper)

### 1️⃣ Clone the repository

```bash
git clone <your-repo-url>
cd PlateMate
```

### 2️⃣ Set up the database

Create a PostgreSQL database named `PlateMate`:

```sql
CREATE DATABASE "PlateMate";
```

### 3️⃣ Configure credentials

Update `src/main/resources/application.properties` with your local settings:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/PlateMate
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

> 💡 **Tip:** For real deployments, keep credentials out of version control — use environment variables or a secrets manager instead of committing them.

### 4️⃣ Run the application

```bash
./mvnw spring-boot:run
```

The API will start on **`http://localhost:8081`** 🎉

---

## 📡 API Reference

Base URL: `http://localhost:8081`

### 👤 User

| Method | Endpoint                | Description               | Body               |
|--------|-------------------------|---------------------------|--------------------|
| `POST` | `/user/register`        | Register a new user       | `UserCreateDTO`    |
| `PUT`  | `/user/change_password` | Change a user's password  | `PasswordChangeDTO`|

#### Example — Register a user

```http
POST http://localhost:8081/user/register
Content-Type: application/json

{
  "name": "Kobiljon",
  "surname": "Odilov",
  "phoneNumber": "+998901234567",
  "password": "Qobil@12",
  "email": "qobilodilov12@gmail.com",
  "nin": "123TJ12",
  "bankDetails": {
    "sortCode": "041019",
    "bankName": "Revolut",
    "accountNumber": "3214342"
  }
}
```

> 📎 Ready-to-run request samples live in [`src/main/resources/http/User.http`](src/main/resources/http/User.http).

> 🚧 **Note:** Waiter, Shift and Holiday services are implemented at the service layer and are being progressively exposed through controllers.

---

## 🛠️ Tech Stack

| Layer            | Technology                          |
|------------------|-------------------------------------|
| Language         | Java 17                             |
| Framework        | Spring Boot 3.5.x                   |
| Persistence      | Spring Data JPA / Hibernate         |
| Database         | PostgreSQL                          |
| Object Mapping   | MapStruct 1.5.5                     |
| Boilerplate      | Lombok                              |
| Messaging *(WIP)*| Spring AMQP / RabbitMQ, Spring Integration |
| Build Tool       | Maven                               |

---

## 🗺️ Roadmap

- [ ] 🔐 Authentication & authorization (Spring Security + JWT) — `AuthService` is scaffolded
- [ ] 🌐 REST controllers for Waiter, Shift and Holiday
- [ ] 🔑 Password hashing (BCrypt) instead of plain-text storage
- [ ] 📨 Async notifications via RabbitMQ (dependencies already in place)
- [ ] 📊 Reporting & analytics for shifts and payroll
- [ ] 🧪 Expanded test coverage

---

## 🧪 Running Tests

```bash
./mvnw test
```

---

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📜 License

This project is currently unlicensed. Add a `LICENSE` file to define usage terms.

---

<p align="center">
  Made with ☕ and 🍽️ for the hospitality industry.
</p>
