# Employee Management System

A Spring Boot-based RESTful API for managing employee records with full CRUD operations, filtering, pagination, sorting, global exception handling, and custom response structure.

---

## 📌 Features

- Create, Read, Update, Delete Employee
- Dashboard API with:
    - Pagination
    - Sorting (by any field)
    - Dynamic filtering (by name, department)
- Global exception handling using `@RestControllerAdvice`
- Custom error response with error codes and descriptions
- Specification-based dynamic querying
- Layered architecture using:
    - Controller → Service → Repository
- Uses `Pageable` and `Specification` from Spring Data JPA
- DTO support for clean API response
- Easily extendable

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot 3+
- Spring Data JPA
- Hibernate
- MySQL / H2
- Maven
- Lombok
- IntelliJ IDEA
- Docker

---

## 🔧 Project Structure

```
src
 └── main
     ├── java
     │   └── com.example.employee
     │       ├── controller
     │       ├── dto
     │       ├── entity
     │       ├── exception
     │       ├── repository
     │       ├── service
     │       │   ├── impl
     │       ├── specification
     │       └── util
     └── resources
         ├── application.yml
```

---

## 🚀 API Endpoints

| Method | Endpoint                    | Description                       |
|--------|-----------------------------|-----------------------------------|
| POST   | `/api/employees`            | Create new employee               |
| GET    | `/api/employees`            | Get all employees (paginated)     |
| GET    | `/api/employees/{id}`       | Get employee by ID                |
| POST   | `/api/employees/{id}`       | Update employee by ID             |
| DELETE | `/api/employees/{id}`       | Delete employee by ID             |
| POST   | `/api/employees/dashboard`  | Dashboard with filters & sorting  |
| GET    | `/api/employees/dashboard/names` | Get list of employee names     |

---

## 📥 Example FilterRequest (for dashboard)

```json
{
  "page": 0,
  "pageSize": 10,
  "sortBy": "name",
  "sortDir": "desc",
  "name": "John",
  "department": "IT"
}
```

---

## 🧪 Testing

- Use Postman to test endpoints
- All endpoints return a unified `ResponseDomain` object
- Proper error messages with codes will be returned from the global exception handler

---

## 📚 Sample Response

```json
{
  "data": {
    "id": 1,
    "name": "John",
    "email": "John@gmail.com",
    "department": "IT"
  },
  "message": "Employee created successfully.",
  "status": 201
}
```

---

## 🧩 Dependencies Highlights

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `mysql-connector-java`
- `lombok`
---

## 👨‍💻 Author
*Navneet Malva*
