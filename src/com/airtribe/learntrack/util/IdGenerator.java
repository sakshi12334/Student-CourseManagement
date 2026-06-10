package com.airtribe.learntrack.util;

public class IdGenerator {

    private static int studentId = 1000;
    private static int courseId = 2000;
    private static int enrollmentId = 3000;

    public static int getNextStudentId() {
        return ++studentId;
    }

    public static int getNextCourseId() {
        return ++courseId;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentId;
    }
}