package com.hoangdeptrai.User;

import com.hoangdeptrai.Course.Course;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Student extends Account {
    private String name;
    private String studentClass;
    private String email;
    private ObservableList<Course> registeredCourses;

    public Student(String username, String password, String name, String studentClass, String email) {
        super(username, password, "Student");
        this.name = name;
        this.studentClass = studentClass;
        this.email = email;
        this.registeredCourses = FXCollections.observableArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ObservableList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
    }

    public void unregisterCourse(Course course) {
        registeredCourses.remove(course);
    }
}
