package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public Optional<Student> findById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }

    public List<Student> findAll() {
        return students;
    }

    public Student update(Student updatedStudent) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == updatedStudent.getId()) {

                students.set(i, updatedStudent);

                return updatedStudent;
            }
        }

        return null;
    }
}