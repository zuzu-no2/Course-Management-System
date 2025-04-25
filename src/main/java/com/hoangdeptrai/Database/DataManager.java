// package com.hoangdeptrai.Database;

// import com.hoangdeptrai.User.Student;  
// import com.hoangdeptrai.Course.Course;

// import java.io.*;
// import java.util.List;
// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;

// public class DataManager {

//     private static final String STUDENT_FILE = "students.txt";
//     private static final String COURSE_FILE = "courses.txt";

//     // Method to save students list to file
//     public static void saveStudents(List<Student> students) {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
//             oos.writeObject(students);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to load students list from file
//     public static ObservableList<Student> loadStudents() {
//         ObservableList<Student> students = FXCollections.observableArrayList();
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
//             students.addAll((List<Student>) ois.readObject());
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//         return students;
//     }

//     // Method to save courses list to file
//     public static void saveCourses(List<Course> courses) {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COURSE_FILE))) {
//             oos.writeObject(courses);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Method to load courses list from file
//     public static ObservableList<Course> loadCourses() {
//         ObservableList<Course> courses = FXCollections.observableArrayList();
//         try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COURSE_FILE))) {
//             courses.addAll((List<Course>) ois.readObject());
//         } catch (IOException | ClassNotFoundException e) {
//             e.printStackTrace();
//         }
//         return courses;
//     }

//     // Method to update a student's courses in the file
//     public static void updateStudentCourses(Student updatedStudent) {
//         ObservableList<Student> students = loadStudents();
//         for (Student student : students) {
//             if (student.getUsername().equals(updatedStudent.getUsername())) {
//                 student.setRegisteredCourses(updatedStudent.getRegisteredCourses());
//                 break;
//             }
//         }
//         saveStudents(students);
//     }

//     // Method to add a course to the courses file
//     public static void addCourse(Course course) {
//         ObservableList<Course> courses = loadCourses();
//         courses.add(course);
//         saveCourses(courses);
//     }

//     // Method to remove a course from the courses file
//     public static void removeCourse(Course course) {
//         ObservableList<Course> courses = loadCourses();
//         courses.removeIf(c -> c.getCourseID().equals(course.getCourseID()));
//         saveCourses(courses);
//     }
// }

