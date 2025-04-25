// package gui;
package com.hoangdeptrai.gui;

import com.hoangdeptrai.Main;
import com.hoangdeptrai.Course.Course;
import com.hoangdeptrai.User.Student;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class StudentPane {
    private BorderPane pane;
    private Main app;
    private Student student;
    private ObservableList<Course> courseList;

    public StudentPane(Main app, Student student, ObservableList<Course> courseList) {
        this.app = app;
        this.student = student;
        this.courseList = courseList;
        pane = new BorderPane();
        VBox studentVBox = new VBox(10);
        studentVBox.setPadding(new Insets(10));

        Label coursesLabel = new Label("Danh sách môn học:");
        ListView<Course> coursesListView = new ListView<>(courseList);

        coursesListView.setCellFactory(param -> new ListCell<Course>() {
            @Override
            protected void updateItem(Course item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getCourseID() + " - " + item.getName() + " - " + item.getCredits() + " tín chỉ");
                }
            }
        });

        Button registerCourseButton = new Button("Đăng kí môn");
        registerCourseButton.setOnAction(e -> {
            Course selectedCourse = coursesListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                showAlert(Alert.AlertType.INFORMATION, "Đăng kí thành công", "Bạn đã đăng kí môn: " + selectedCourse.getName());
                student.registerCourse(selectedCourse);
            }
        });

        Label registeredCoursesLabel = new Label("Danh sách môn đã đăng kí:");
        ListView<Course> registeredCoursesListView = new ListView<>(student.getRegisteredCourses());

        registeredCoursesListView.setCellFactory(param -> new ListCell<Course>() {
            @Override
            protected void updateItem(Course item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getCourseID() + " - " + item.getName() + " - " + item.getCredits() + " tín chỉ");
                }
            }
        });

        Button unregisterCourseButton = new Button("Hủy đăng kí");
        unregisterCourseButton.setOnAction(e -> {
            Course selectedCourse = registeredCoursesListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                showAlert(Alert.AlertType.INFORMATION, "Hủy đăng kí thành công", "Bạn đã hủy đăng kí môn: " + selectedCourse.getName());
                student.unregisterCourse(selectedCourse);
            }
        });

        studentVBox.getChildren().addAll(coursesLabel, coursesListView, registerCourseButton,
                                         registeredCoursesLabel, registeredCoursesListView, unregisterCourseButton);

        pane.setCenter(studentVBox);

        Button logoutButton = new Button("Đăng xuất");
        logoutButton.setOnAction(e -> app.setLoginScene());
        pane.setBottom(logoutButton);
        BorderPane.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(logoutButton, new Insets(10));
    }

    public BorderPane getPane() {
        return pane;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
