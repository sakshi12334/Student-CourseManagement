package com.airtribe.learntrack.service;

import java.util.List;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;

public class StudentService {

    private final StudentRepository repo = new StudentRepository();

    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Student student) {
        return repo.update(student);
    }

    public Student getById(int id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Student not found with id: " + id));
    }

    public List<Student> listStudents() {
        return repo.findAll();
    }

    public void deactivateStudent(int id) {

        Student student = getById(id);

        student.setActive(false);

        repo.update(student);
    }

    public void activateStudent(int id) {

        Student student = getById(id);

        student.setActive(true);

        repo.update(student);
    }
}