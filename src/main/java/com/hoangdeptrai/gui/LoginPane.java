package com.hoangdeptrai.gui;

import com.hoangdeptrai.Main;
import com.hoangdeptrai.User.Student;
import com.hoangdeptrai.Course.Course;
import com.hoangdeptrai.gui.TeacherPane;

// import Main.Main;

// import User.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class LoginPane {
    private BorderPane pane;
    private Main app;
    private ObservableList<Student> studentsList;
    private ObservableList<Course> courseList;

    public LoginPane(Main app) {
        this.app = app;
        this.studentsList = FXCollections.observableArrayList();
        this.courseList = FXCollections.observableArrayList(
            new Course("MH1", "Tên môn học 1", 3),
            new Course("MH2", "Tên môn học 2", 2),
            new Course("MH3", "Tên môn học 3", 4)
        );
        pane = new BorderPane();

        TabPane tabPane = new TabPane();
        Tab tabRegister = createRegisterTab();
        Tab tabLogin = createLoginTab();

        tabPane.getTabs().addAll(tabRegister, tabLogin);
        pane.setCenter(tabPane);
    }

    public BorderPane getPane() {
        return pane;
    }

    public ObservableList<Student> getStudentsList() {
        return studentsList;
    }

    public ObservableList<Course> getCourseList() {
        return courseList;
    }

    private Tab createRegisterTab() {
        Tab tabRegister = new Tab("Đăng kí sinh viên");
        GridPane registerPane = new GridPane();
        registerPane.setPadding(new Insets(150, 150, 150, 150));
        registerPane.setVgap(8);
        registerPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        Label classLabel = new Label("Class:");
        TextField classInput = new TextField();
        Label usernameLabel = new Label("Username:");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordInput = new PasswordField();
        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();

        Button registerButton = new Button("Đăng kí");
        registerButton.setOnAction(e -> {
            if (nameInput.getText().isEmpty() || classInput.getText().isEmpty() || 
                usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty() || 
                emailInput.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Đăng kí thất bại", "Thông tin không được để trống.");
            } else {
                Student newStudent = new Student(usernameInput.getText(), passwordInput.getText(),
                        nameInput.getText(), classInput.getText(), emailInput.getText());
                studentsList.add(newStudent);
                showAlert(Alert.AlertType.INFORMATION, "Đăng kí thành công", "Sinh viên đã được đăng kí.");
                nameInput.clear();
                classInput.clear();
                usernameInput.clear();
                passwordInput.clear();
                emailInput.clear();
            }
        });

        registerPane.add(nameLabel, 0, 0);
        registerPane.add(nameInput, 1, 0);
        registerPane.add(classLabel, 0, 1);
        registerPane.add(classInput, 1, 1);
        registerPane.add(usernameLabel, 0, 2);
        registerPane.add(usernameInput, 1, 2);
        registerPane.add(passwordLabel, 0, 3);
        registerPane.add(passwordInput, 1, 3);
        registerPane.add(emailLabel, 0, 4);
        registerPane.add(emailInput, 1, 4);
        registerPane.add(registerButton, 1, 5);

        tabRegister.setContent(registerPane);
        return tabRegister;
    }

    private Tab createLoginTab() {
        Tab tabLogin = new Tab("Đăng Nhập");
        GridPane loginPane = new GridPane();
        loginPane.setPadding(new Insets(10, 10, 10, 10));
        loginPane.setVgap(8);
        loginPane.setHgap(10);

        Label loginUsernameLabel = new Label("Username:");
        TextField loginUsernameInput = new TextField();
        Label loginPasswordLabel = new Label("Password:");
        PasswordField loginPasswordInput = new PasswordField();
        Label roleLabel = new Label("Role:");
        ChoiceBox<String> roleChoiceBox = new ChoiceBox<>();
        roleChoiceBox.getItems().addAll("Student", "Teacher");

        Button loginButton = new Button("Đăng nhập");
        loginButton.setOnAction(e -> {
            String role = roleChoiceBox.getValue();
            String username = loginUsernameInput.getText();
            String password = loginPasswordInput.getText();

            if (username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Đăng nhập thất bại", "Thông tin không được để trống.");
            } else if (role.equals("Teacher") && username.equals("admin") && password.equals("admin")) {
                showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công", "Chào mừng Admin.");
                app.setTeacherScene(new TeacherPane(app, studentsList, courseList));
            } else if (role.equals("Student")) {
                Student student = studentsList.stream()
                    .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                    .findFirst().orElse(null);
                if (student != null) {
                    showAlert(Alert.AlertType.INFORMATION, "Đăng nhập thành công", "Chào mừng sinh viên.");
                    app.setStudentScene(new StudentPane(app, student, courseList));
                } else {
                    showAlert(Alert.AlertType.ERROR, "Đăng nhập thất bại", "Thông tin đăng nhập không chính xác.");
                }
            }
        });

        loginPane.add(loginUsernameLabel, 0, 0);
        loginPane.add(loginUsernameInput, 1, 0);
        loginPane.add(loginPasswordLabel, 0, 1);
        loginPane.add(loginPasswordInput, 1, 1);
        loginPane.add(roleLabel, 0, 2);
        loginPane.add(roleChoiceBox, 1, 2);
        loginPane.add(loginButton, 1, 3);

        tabLogin.setContent(loginPane);
        return tabLogin;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
