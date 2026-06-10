package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public Enrollment save(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public Optional<Enrollment> findById(int id) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() == id) {
                return Optional.of(enrollment);
            }
        }

        return Optional.empty();
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public Enrollment update(Enrollment updatedEnrollment) {

        for (int i = 0; i < enrollments.size(); i++) {

            if (enrollments.get(i).getId() == updatedEnrollment.getId()) {

                enrollments.set(i, updatedEnrollment);

                return updatedEnrollment;
            }
        }

        return null;
    }
}