package com.example.assignment2_students_app.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Model {
    static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private Model() {
        for (int i = 0; i < 10; i++) {
            addStudent(new Student("Student " + i, "" + i, 84226713, "Yahud","", false));
        }
    }

    List<Student> studentsList = new LinkedList<>();

    public List<Student> getAllStudents() {
        return studentsList;
    }

    public void addStudent(Student student) {
        studentsList.add(student);
    }

    public void deleteStudent(int studentIndex) {
        try {
            studentsList.remove(studentIndex);
        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }

    public void editStudent(int studentIndex, Student updatedStudent) {
        try {
            studentsList.set(studentIndex, updatedStudent);
        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
        }
    }
}
