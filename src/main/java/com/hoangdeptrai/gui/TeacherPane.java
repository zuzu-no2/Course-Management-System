package com.hoangdeptrai.gui;

import com.hoangdeptrai.User.Student;
import com.hoangdeptrai.Main;
import com.hoangdeptrai.Course.Course;


// import Main.Main;
// import User.Student;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class TeacherPane {
    private BorderPane pane;
    private Main app;
    private ObservableList<Student> studentsList;
    private ObservableList<Course> courseList;

    public TeacherPane(Main app, ObservableList<Student> studentsList, ObservableList<Course> courseList) {
        this.app = app;
        this.studentsList = studentsList;
        this.courseList = courseList;
        initialize();
    }

    private void initialize() {
        pane = new BorderPane();
        VBox teacherVBox = new VBox(10);
        teacherVBox.setPadding(new Insets(10));

        Label studentsLabel = new Label("Danh sách sinh viên:");
        TableView<Student> studentsTableView = new TableView<>(studentsList);
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Student, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        studentsTableView.getColumns().addAll(nameColumn, usernameColumn);

        studentsTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Student selectedStudent = studentsTableView.getSelectionModel().getSelectedItem();
                if (selectedStudent != null) {
                    showStudentCoursesDialog(selectedStudent);
                }
            }
        });

        teacherVBox.getChildren().addAll(studentsLabel, studentsTableView);

        // Section for managing courses
        Label coursesLabel = new Label("Quản lý môn học:");
        TableView<Course> coursesTableView = new TableView<>(courseList);
        TableColumn<Course, String> courseIDColumn = new TableColumn<>("Course ID");
        courseIDColumn.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Name");
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Course, Integer> courseCreditsColumn = new TableColumn<>("Credits");
        courseCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        coursesTableView.getColumns().addAll(courseIDColumn, courseNameColumn, courseCreditsColumn);

        TextField courseIDInput = new TextField();
        courseIDInput.setPromptText("Course ID");
        TextField courseNameInput = new TextField();
        courseNameInput.setPromptText("Name");
        TextField courseCreditsInput = new TextField();
        courseCreditsInput.setPromptText("Credits");

        Button addCourseButton = new Button("Thêm môn học");
        addCourseButton.setOnAction(e -> {
            String id = courseIDInput.getText();
            String name = courseNameInput.getText();
            int credits = Integer.parseInt(courseCreditsInput.getText());
            Course newCourse = new Course(id, name, credits);
            courseList.add(newCourse);
            courseIDInput.clear();
            courseNameInput.clear();
            courseCreditsInput.clear();
        });

        Button deleteCourseButton = new Button("Xóa môn học");
        deleteCourseButton.setOnAction(e -> {
            Course selectedCourse = coursesTableView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                courseList.remove(selectedCourse);
            }
        });

        HBox courseInputBox = new HBox(10);
        courseInputBox.getChildren().addAll(courseIDInput, courseNameInput, courseCreditsInput, addCourseButton, deleteCourseButton);

        teacherVBox.getChildren().addAll(coursesLabel, coursesTableView, courseInputBox);

        pane.setCenter(teacherVBox);

        Button logoutButton = new Button("Đăng xuất");
        logoutButton.setOnAction(e -> app.setLoginScene());
        pane.setBottom(logoutButton);
        BorderPane.setAlignment(logoutButton, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(logoutButton, new Insets(10));
    }

    public BorderPane getPane() {
        return pane;
    }

    private void showStudentCoursesDialog(Student student) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Môn học của sinh viên");
        dialog.setHeaderText("Các môn học mà " + student.getName() + " đã đăng kí:");

        ListView<Course> studentCoursesListView = new ListView<>(student.getRegisteredCourses());

        studentCoursesListView.setCellFactory(param -> new ListCell<Course>() {
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

        Button removeCourseButton = new Button("Hủy môn");
        removeCourseButton.setOnAction(e -> {
            Course selectedCourse = studentCoursesListView.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                showAlert(Alert.AlertType.INFORMATION, "Hủy môn thành công", "Bạn đã hủy môn: " + selectedCourse.getName());
                student.unregisterCourse(selectedCourse);
                studentCoursesListView.getItems().remove(selectedCourse);
            }
        });

        VBox dialogVBox = new VBox(10);
        dialogVBox.getChildren().addAll(studentCoursesListView, removeCourseButton);
        dialogVBox.setPadding(new Insets(10));

        dialog.getDialogPane().setContent(dialogVBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
