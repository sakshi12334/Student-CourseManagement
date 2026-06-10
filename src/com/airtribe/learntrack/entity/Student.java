package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    // Constructor overloading
    public Student() {
        super();
        this.batch = "";
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email,
                   String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public Student(int id, String firstName, String lastName,
                   String batch, boolean active) {
        super(id, firstName, lastName, "");
        this.batch = batch;
        this.active = active;
    }

    // Getters
    public String getBatch() {
        return batch;
    }

    public boolean isActive() {
        return active;
    }

    // Setters
    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Override getDisplayName from Person
    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}