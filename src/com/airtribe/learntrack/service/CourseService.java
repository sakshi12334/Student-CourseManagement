package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;

/**
 * Service layer for course management.
 * Handles business logic for course operations including adding, updating,
 * searching, and managing course status.
 */
public class CourseService {

    private final CourseRepository repo = new CourseRepository();

    /**
     * Add a new course to the system.
     */
    public Course addCourse(Course course) {
        return repo.save(course);
    }

    /**
     * Update an existing course.
     */
    public Course updateCourse(Course course) {
        return repo.update(course);
    }

    /**
     * Get a course by ID.
     * Throws EntityNotFoundException if course not found.
     */
    public Course getById(int id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Course not found with id: " + id));
    }

    /**
     * List all courses in the system.
     */
    public List<Course> listCourses() {
        return repo.findAll();
    }

    /**
     * Activate a course by ID.
     */
    public void activateCourse(int id) {

        Course course = getById(id);

        course.setActive(true);

        repo.update(course);
    }

    /**
     * Deactivate a course by ID.
     */
    public void deactivateCourse(int id) {

        Course course = getById(id);

        course.setActive(false);

        repo.update(course);
    }
}