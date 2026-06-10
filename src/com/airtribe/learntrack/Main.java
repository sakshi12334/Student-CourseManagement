package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {
        initializeApplication();
        displayWelcomeMessage();
        runApplicationMenu();
    }


    private static void initializeApplication() {
        scanner = new Scanner(System.in);
        studentService = new StudentService();
        courseService = new CourseService();
        enrollmentService = new EnrollmentService(studentService, courseService);
    }

 
    private static void displayWelcomeMessage() {
        System.out.println("\n========================================");
        System.out.println(AppConstants.WELCOME_MESSAGE);
        System.out.println("Version: " + AppConstants.VERSION);
        System.out.println("========================================\n");
    }

    private static void runApplicationMenu() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = readMenuChoice();

            try {
                switch (choice) {
                    case MenuOptions.ADD_STUDENT:
                        handleAddStudent();
                        break;
                    case MenuOptions.VIEW_ALL_STUDENTS:
                        handleViewAllStudents();
                        break;
                    case MenuOptions.SEARCH_STUDENT_BY_ID:
                        handleSearchStudent();
                        break;
                    case MenuOptions.DEACTIVATE_STUDENT:
                        handleDeactivateStudent();
                        break;
                    case MenuOptions.ADD_COURSE:
                        handleAddCourse();
                        break;
                    case MenuOptions.VIEW_ALL_COURSES:
                        handleViewAllCourses();
                        break;
                    case MenuOptions.ACTIVATE_COURSE:
                        handleActivateCourse();
                        break;
                    case MenuOptions.DEACTIVATE_COURSE:
                        handleDeactivateCourse();
                        break;
                    case MenuOptions.ENROLL_STUDENT:
                        handleEnrollStudent();
                        break;
                    case MenuOptions.VIEW_STUDENT_ENROLLMENTS:
                        handleViewStudentEnrollments();
                        break;
                    case MenuOptions.MARK_ENROLLMENT_COMPLETED:
                        handleMarkEnrollmentCompleted();
                        break;
                    case MenuOptions.MARK_ENROLLMENT_CANCELLED:
                        handleMarkEnrollmentCancelled();
                        break;
                    case MenuOptions.EXIT:
                        running = false;
                        displayExitMessage();
                        break;
                    default:
                        System.out.println("Invalid choice. " + AppConstants.INVALID_INPUT);
                }
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n" + AppConstants.MENU_TITLE);
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student By ID");
        System.out.println("4. Deactivate Student");
        System.out.println("\n--- Course Management ---");
        System.out.println("5. Add Course");
        System.out.println("6. View All Courses");
        System.out.println("7. Activate Course");
        System.out.println("8. Deactivate Course");
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("9. Enroll Student");
        System.out.println("10. View Student Enrollments");
        System.out.println("11. Mark Enrollment Completed");
        System.out.println("12. Mark Enrollment Cancelled");
        System.out.println("\n0. Exit");
        System.out.print("\nEnter your choice: ");
    }


    private static int readMenuChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Please enter a valid number");
        }
    }


    private static void handleAddStudent() {
        System.out.println("\n--- Add New Student ---");

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(firstName, "First Name");

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(lastName, "Last Name");

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(email, "Email");

        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(batch, "Batch");

        int studentId = IdGenerator.getNextStudentId();
        Student student = new Student(studentId, firstName, lastName, email, batch, true);

        studentService.addStudent(student);
        System.out.println(AppConstants.OPERATION_SUCCESSFUL);
        System.out.println("Student added with ID: " + studentId);
    }


    private static void handleViewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void handleSearchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID: ");

        try {
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            Student student = studentService.getById(studentId);
            System.out.println(student);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number");
        }
    }

    private static void handleDeactivateStudent() {
        System.out.println("\n--- Deactivate Student ---");
        System.out.print("Enter Student ID: ");

        try {
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            studentService.deactivateStudent(studentId);
            System.out.println(AppConstants.OPERATION_SUCCESSFUL);
            System.out.println("Student " + studentId + " has been deactivated.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number");
        }
    }

 
    private static void handleAddCourse() {
        System.out.println("\n--- Add New Course ---");

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(courseName, "Course Name");

        System.out.print("Enter Description: ");
        String description = scanner.nextLine().trim();
        InputValidator.requireNonEmpty(description, "Description");

        System.out.print("Enter Duration (weeks): ");
        int duration;
        try {
            duration = Integer.parseInt(scanner.nextLine().trim());
            if (duration < 1 || duration > 52) {
                throw new InvalidInputException("Duration must be between 1 and 52 weeks");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Duration must be a number");
        }

        int courseId = IdGenerator.getNextCourseId();
        Course course = new Course(courseId, courseName, description, duration, true);

        courseService.addCourse(course);
        System.out.println(AppConstants.OPERATION_SUCCESSFUL);
        System.out.println("Course added with ID: " + courseId);
    }

    private static void handleViewAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = courseService.listCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }


    private static void handleActivateCourse() {
        System.out.println("\n--- Activate Course ---");
        System.out.print("Enter Course ID: ");

        try {
            int courseId = Integer.parseInt(scanner.nextLine().trim());
            courseService.activateCourse(courseId);
            System.out.println(AppConstants.OPERATION_SUCCESSFUL);
            System.out.println("Course " + courseId + " has been activated.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Course ID must be a number");
        }
    }

    private static void handleDeactivateCourse() {
        System.out.println("\n--- Deactivate Course ---");
        System.out.print("Enter Course ID: ");

        try {
            int courseId = Integer.parseInt(scanner.nextLine().trim());
            courseService.deactivateCourse(courseId);
            System.out.println(AppConstants.OPERATION_SUCCESSFUL);
            System.out.println("Course " + courseId + " has been deactivated.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Course ID must be a number");
        }
    }


    private static void handleEnrollStudent() {
        System.out.println("\n--- Enroll Student ---");

        System.out.print("Enter Student ID: ");
        int studentId;
        try {
            studentId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number");
        }

        System.out.print("Enter Course ID: ");
        int courseId;
        try {
            courseId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Course ID must be a number");
        }

        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println(AppConstants.OPERATION_SUCCESSFUL);
        System.out.println("Student enrolled with Enrollment ID: " + enrollment.getId());
    }

    private static void handleViewStudentEnrollments() {
        System.out.println("\n--- View Student Enrollments ---");
        System.out.print("Enter Student ID: ");

        try {
            int studentId = Integer.parseInt(scanner.nextLine().trim());
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);

            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for this student.");
                return;
            }

            System.out.println("Enrollments for Student " + studentId + ":");
            for (Enrollment enrollment : enrollments) {
                System.out.println(enrollment);
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Student ID must be a number");
        }
    }


    private static void handleMarkEnrollmentCompleted() {
        System.out.println("\n--- Mark Enrollment Completed ---");
        System.out.print("Enter Enrollment ID: ");

        try {
            int enrollmentId = Integer.parseInt(scanner.nextLine().trim());
            enrollmentService.completeEnrollment(enrollmentId);
            System.out.println(AppConstants.OPERATION_SUCCESSFUL);
            System.out.println("Enrollment " + enrollmentId + " marked as completed.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Enrollment ID must be a number");
        }
    }

 
    private static void handleMarkEnrollmentCancelled() {
        System.out.println("\n--- Mark Enrollment Cancelled ---");
        System.out.print("Enter Enrollment ID: ");

        try {
            int enrollmentId = Integer.parseInt(scanner.nextLine().trim());
            enrollmentService.cancelEnrollment(enrollmentId);
            System.out.println(AppConstants.OPERATION_SUCCESSFUL);
            System.out.println("Enrollment " + enrollmentId + " marked as cancelled.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Enrollment ID must be a number");
        }
    }

    private static void displayExitMessage() {
        System.out.println("\nThank you for using LearnTrack!");
        System.out.println("Goodbye!");
    }
}

