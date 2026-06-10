# LearnTrack - Student & Course Management System

## Project Overview

LearnTrack is a Core Java console-based application for managing students, courses, and enrollments. It demonstrates fundamental Object-Oriented Programming (OOP) concepts including inheritance, encapsulation, abstraction, and polymorphism using only Core Java fundamentals without any external frameworks or libraries.

## Features

### Student Management
- Add new students to the system
- View all students
- Search for students by ID
- Deactivate students
- Track student batch information and active status

### Course Management
- Add new courses with detailed information
- View all available courses
- Activate/Deactivate courses
- Set course duration in weeks

### Enrollment Management
- Enroll students in courses with validation
- View all enrollments
- Get enrollments for a specific student
- Mark enrollments as completed
- Cancel enrollments
- Track enrollment dates and status (Active, Completed, Cancelled)

## Project Structure

```
src/
└── com/
    └── airtribe/
        └── learntrack/
            ├── Main.java                              # Main entry point
            │
            ├── entity/                                # Data models
            │   ├── Person.java                        # Base class
            │   ├── Student.java                       # Extends Person
            │   ├── Course.java                        # Course entity
            │   └── Enrollment.java                    # Enrollment entity
            │
            ├── repository/                            # Data access layer
            │   ├── StudentRepository.java
            │   ├── CourseRepository.java
            │   └── EnrollmentRepository.java
            │
            ├── service/                               # Business logic layer
            │   ├── StudentService.java
            │   ├── CourseService.java
            │   └── EnrollmentService.java
            │
            ├── exception/                             # Custom exceptions
            │   ├── EntityNotFoundException.java
            │   └── InvalidInputException.java
            │
            ├── util/                                  # Utility classes
            │   ├── IdGenerator.java                   # Static ID generation
            │   └── InputValidator.java                # Input validation
            │
            ├── constants/                             # Application constants
            │   ├── MenuOptions.java
            │   └── AppConstants.java
            │
            └── enums/                                 # Enumerations
                ├── EnrollmentStatus.java
                └── CourseStatus.java

docs/
├── Setup_Instructions.md                             # Setup guide
├── JVM_Basics.md                                     # JVM documentation
└── Design_Notes.md                                   # Architecture notes
```

## OOP Concepts Demonstrated

### 1. **Inheritance**
- `Student` class extends `Person` base class
- Demonstrates use of `super()` keyword
- Method overriding with `getDisplayName()`

### 2. **Encapsulation**
- All entity fields are `private`
- Public getters and setters for controlled access
- Constructor overloading for flexible object creation

### 3. **Polymorphism**
- Method overriding in Student class
- `toString()` methods in all entities
- Service layer polymorphism

### 4. **Abstraction**
- Repository pattern for data access abstraction
- Service layer abstraction
- Custom exception classes

### 5. **Static Members**
- `IdGenerator` class uses static counters and methods
- Maintains ID sequences across the application lifetime

## Compilation and Execution

### Prerequisites
- JDK 8 or higher installed
- Command line/Terminal access

### Compile

Navigate to the project root directory and compile all Java files:

```bash
javac -d bin src/com/airtribe/learntrack/**/*.java
```

Or compile all files at once:

```bash
javac -d bin src/com/airtribe/learntrack/*.java
javac -d bin src/com/airtribe/learntrack/entity/*.java
javac -d bin src/com/airtribe/learntrack/repository/*.java
javac -d bin src/com/airtribe/learntrack/service/*.java
javac -d bin src/com/airtribe/learntrack/exception/*.java
javac -d bin src/com/airtribe/learntrack/util/*.java
javac -d bin src/com/airtribe/learntrack/constants/*.java
javac -d bin src/com/airtribe/learntrack/enums/*.java
```

### Run

```bash
java -cp bin com.airtribe.learntrack.Main
```

## Usage Guide

### Main Menu
The application displays a menu-driven interface with the following options:

```
=== Main Menu ===

--- Student Management ---
1. Add Student
2. View All Students
3. Search Student By ID
4. Deactivate Student

--- Course Management ---
5. Add Course
6. View All Courses
7. Activate Course
8. Deactivate Course

--- Enrollment Management ---
9. Enroll Student
10. View Student Enrollments
11. Mark Enrollment Completed
12. Mark Enrollment Cancelled

0. Exit
```

### Example Workflow

1. **Add a Student**: Select option 1, enter first name, last name, email, and batch
2. **Add a Course**: Select option 5, enter course name, description, and duration
3. **Enroll Student**: Select option 9, enter student ID and course ID
4. **View Enrollments**: Select option 10, enter student ID to see all enrollments

## Key Classes

### Entity Classes
- **Person**: Base class with common attributes (id, firstName, lastName, email)
- **Student**: Extends Person, adds batch and active status
- **Course**: Represents a course with name, description, and duration
- **Enrollment**: Links students to courses with dates and status

### Service Classes
- **StudentService**: Business logic for student operations
- **CourseService**: Business logic for course operations
- **EnrollmentService**: Business logic for enrollment operations

### Repository Classes
- Uses `ArrayList<T>` for data storage
- Implements CRUD operations: Create, Read, Update, Delete
- Provides `Optional<T>` for null-safe retrieval

### Utility Classes
- **IdGenerator**: Generates unique IDs using static counters
- **InputValidator**: Validates user input

## Exception Handling

The application implements comprehensive exception handling:
- `EntityNotFoundException`: Thrown when searching for non-existent entities
- `InvalidInputException`: Thrown for invalid user input
- `NumberFormatException`: Caught and converted to user-friendly messages

All invalid inputs are handled gracefully without crashing the application.

## Design Patterns Used

### 1. **Repository Pattern**
- Abstracts data access layer
- Makes it easy to switch storage mechanisms (ArrayList to Database)

### 2. **Service Layer Pattern**
- Separates business logic from presentation and persistence
- Provides reusable operations

### 3. **Singleton Pattern** (via Static Methods)
- IdGenerator uses static methods for globally accessible ID generation

## Coding Standards

- **Meaningful Names**: Classes, methods, and variables have descriptive names
- **Small Methods**: Each method has a single responsibility
- **Package Organization**: Related classes grouped in logical packages
- **Comments**: Javadoc comments explain purpose of classes and complex logic
- **Clean Formatting**: Consistent indentation and spacing

## Technical Details

- **Language**: Core Java (JDK 8+)
- **Collections**: ArrayList only (no Streams, Collections framework utilities)
- **Type Safety**: No generic collections (simplified for Core Java focus)
- **Concurrency**: No multithreading
- **I/O**: Scanner for console input

## Error Handling

The application demonstrates proper error handling:
- Try-catch blocks for exception handling
- Validation before operations
- User-friendly error messages
- Application continues running after errors

## Future Enhancements

Possible extensions (maintaining Core Java focus):
- File persistence (Serialization)
- Enhanced validation rules
- Search by name functionality
- Course prerequisites
- Student GPA tracking
- Batch operations

## Author

Created as a Core Java assignment for LearnTrack System.

## License

Educational Use Only
