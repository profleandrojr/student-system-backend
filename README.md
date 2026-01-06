# Student Management System Backend

A robust RESTful API for managing university students, teachers, classes, and grades. Built with **Java 21**, **Spring Boot 3**, and **MySQL**.

## 🚀 Tech Stack

- **Java 21**: Records, Pattern Matching, Virtual Threads ready.
- **Spring Boot 3**: Web, Data JPA, Validation.
- **Database**: MySQL (Production), H2 (Test).
- **Architecture**: DTO Pattern, Global Exception Handling, Repository Pattern.
- **Inheritance Strategy**: JPA `JOINED` Strategy for Users (Students/Teachers).

## 🛠 Features

- **User Management**: Distinct registration flows for Students and Teachers (extending a base User entity).
- **Classroom Control**:
  - Create Topics and Assign Teachers.
  - Create Classes (Linking Topic + Teacher).
  - Enroll Students (Many-to-Many relationship).
- **Academic Operations**:
  - **Lectures**: Schedule lectures for specific classes.
  - **Presence**: Student check-in validation (must be enrolled + lecture active).
  - **Grades**: Assign and retrieve grades with strict validation.
- **Safety**: Global Exception Handling returning clean JSON errors (404, 400).

## 🔌 API Endpoints

### Actors

- `POST /api/v1/students` - Register a new Student.
- `POST /api/v1/teachers` - Register a new Teacher.

### Academic Structure

- `POST /api/v1/topics` - Create a subject (e.g., "Java 21").
- `POST /api/v1/classrooms` - Open a class for a Topic.
- `PUT /api/v1/classrooms/{id}/students/{studentId}` - Enroll a Student.

### Daily Operations

- `POST /api/v1/lectures` - Start a new Lecture.
- `POST /api/v1/presences/check-in` - Student Check-in.

### Evaluation

- `POST /api/v1/grades` - Assign a Grade.
- `GET /api/v1/grades/student/{id}` - View Student Report.

## 🏃‍♂️ How to Run

1.  **Clone the repo**:
    ```bash
    git clone [Student System Backend](https://github.com/profleandrojr/student-system-backend.git)
    ```
2.  **Configure Database**:
    Update `src/main/resources/application.properties` with your MySQL credentials.
3.  **Run**:
    ```bash
    ./mvnw spring-boot:run
    ```
