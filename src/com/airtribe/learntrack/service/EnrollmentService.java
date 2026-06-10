package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService,
                             CourseService courseService) {

        this.enrollmentRepository = new EnrollmentRepository();
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {

        // Validate Student
        Student student = studentService.getById(studentId);

        // Validate Course
        Course course = courseService.getById(courseId);

        Enrollment enrollment = new Enrollment();

        enrollment.setId(IdGenerator.getNextEnrollmentId());
        enrollment.setStudentId(student.getId());
        enrollment.setCourseId(course.getId());
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment getEnrollmentById(int enrollmentId) {

        return enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Enrollment not found with id: " + enrollmentId));
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollmentRepository.findAll()) {

            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public void completeEnrollment(int enrollmentId) {

        Enrollment enrollment = getEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.COMPLETED);

        enrollmentRepository.update(enrollment);
    }

    public void cancelEnrollment(int enrollmentId) {

        Enrollment enrollment = getEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.CANCELLED);

        enrollmentRepository.update(enrollment);
    }

    public List<Enrollment> listEnrollments() {
        return enrollmentRepository.findAll();
    }
}