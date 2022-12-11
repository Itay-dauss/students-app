package com.example.assignment2_students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.assignment2_students_app.model.Model;
import com.example.assignment2_students_app.model.Student;

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        getSupportActionBar().setTitle("New Student");

        EditText namePt = findViewById(R.id.new_student_name_pt);
        EditText idPt = findViewById(R.id.new_student_id_pt);
        EditText phonePt = findViewById(R.id.new_student_phone_pt);
        EditText addressPt = findViewById(R.id.new_student_address_pt);
        CheckBox checkedCb = findViewById(R.id.new_student_checked_cb);

        Button saveNewStudentBtn = findViewById(R.id.new_student_save_button);
        Button cancelNewStudentBtn = findViewById(R.id.new_student_cancel_button);

        cancelNewStudentBtn.setOnClickListener(view -> {
            Intent studentsListIntent = new Intent(NewStudentActivity.this, StudentsListActivity.class);
            startActivity(studentsListIntent);
        });

        saveNewStudentBtn.setOnClickListener(view -> {
            Student newStudent = new Student(namePt.getText().toString(), idPt.getText().toString(), Long.parseLong(phonePt.getText().toString()), addressPt.getText().toString(), "",checkedCb.isChecked());
            Model.getInstance().addStudent(newStudent);
            Intent studentsListIntent = new Intent(NewStudentActivity.this, StudentsListActivity.class);
            startActivity(studentsListIntent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent StudentsListIntent = new Intent(NewStudentActivity.this, StudentsListActivity.class);
        startActivity(StudentsListIntent);
    }

}