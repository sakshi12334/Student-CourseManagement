package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public Course save(Course course) {
        courses.add(course);
        return course;
    }

    public Optional<Course> findById(int id) {

        for (Course course : courses) {

            if (course.getId() == id) {
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Course update(Course updatedCourse) {

        for (int i = 0; i < courses.size(); i++) {

            if (courses.get(i).getId() == updatedCourse.getId()) {

                courses.set(i, updatedCourse);

                return updatedCourse;
            }
        }

        return null;
    }
}