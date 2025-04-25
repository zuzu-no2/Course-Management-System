package com.hoangdeptrai;

import com.hoangdeptrai.gui.LoginPane;
import com.hoangdeptrai.gui.StudentPane;
import com.hoangdeptrai.gui.TeacherPane;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;
    private Scene loginScene, studentScene, teacherScene;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Student Management System");

        LoginPane loginPane = new LoginPane(this);
        loginScene = new Scene(loginPane.getPane(), 1000, 600);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public void setStudentScene(StudentPane studentPane) {
        studentScene = new Scene(studentPane.getPane(), 1000, 600);
        primaryStage.setScene(studentScene);
    }

    public void setTeacherScene(TeacherPane teacherPane) {
        teacherScene = new Scene(teacherPane.getPane(), 1000, 600);
        primaryStage.setScene(teacherScene);
    }

    public void setLoginScene() {
        primaryStage.setScene(loginScene);
    }
}

