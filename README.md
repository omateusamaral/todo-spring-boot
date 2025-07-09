# 📘 TODO Project with Java 21 + Spring Boot

This is a **learning project** built to explore and practice the concepts of **Spring Boot** using *
*Java 21**, **Docker**, **Maven**, and **PostgreSQL**.

The application is a RESTful API for managing a TODO list, with endpoints to create, read, update,
and delete tasks.

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Docker & Docker Compose**
- **JUnit & Mockito** for automated tests

---

## 🚀 How to Run the Project

### Prerequisites

- Docker and Docker Compose installed
- Java 21 installed (if you want to run it outside Docker)
- Maven 3.x installed

### Running with Docker

1. Clone the repository:

```bash
git clone git@github.com:omateusamaral/todo-spring-boot.git
cd todo-spring-boot


docker-compose up --build


http://localhost:8080
```

# 📂 Project Structure

```bash
src/
├── main/
│   ├── java/
│   │   └── io.github.com.omateusamaral.todo_spring_boot/
│   │       ├── controllers/
│   │       └── dtos/
│   │       ├── entities/
│   │       └── exceptions/
│   │       └── mappers/
│   │       └── repositories/
│   │       └── services/
│   │       └── specificaitons/
│   └── resources/
│       ├── application.yml
├── test/
│   └── java/
│       └── io.github.com.omateusamaral.todo_spring_boot/
│           └── (Unit tests)
```

# ✅ Features

- Create task

- List all tasks

- Update task

- Delete task

- Automated tests using JUnit and Mockito

# 📌 Notes

This project is for educational purposes and serves as a foundation for learning and experimenting
with Java + Spring Boot + Docker stack. Contributions and suggestions are welcome!