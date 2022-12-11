package com.example.assignment2_students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignment2_students_app.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {
    Student student;
    int studentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_details);
        getSupportActionBar().setTitle("Students Details");

        student = getIntent().getParcelableExtra("studentDetails");
        studentIndex = getIntent().getIntExtra("studentIndex", -1);
        setStudentData(student);

        Button editStudentBtn = findViewById(R.id.edit_student_button);
        editStudentBtn.setOnClickListener(view -> {
            Intent editStudentIntent = new Intent(StudentDetailsActivity.this, EditStudentActivity.class);
            editStudentIntent.putExtra("studentDetails", student);
            editStudentIntent.putExtra("studentIndex", studentIndex);
            startActivity(editStudentIntent);
        });

    }

    @SuppressLint("SetTextI18n")
    private void setStudentData(Student student) {
        TextView nameTv = findViewById(R.id.student_name_value);
        TextView idTv = findViewById(R.id.student_id_value);
        TextView phoneTv = findViewById(R.id.student_phone_value);
        TextView addressTv = findViewById(R.id.student_address_value);
        CheckBox checkedCb = findViewById(R.id.student_checked_cb_disabled);

        nameTv.setText(student.name);
        idTv.setText(student.id);
        phoneTv.setText(Long.toString(student.phone));
        addressTv.setText(student.address);
        checkedCb.setChecked(student.cb);
        checkedCb.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        Intent studentsListIntent = new Intent(StudentDetailsActivity.this, StudentsListActivity.class);
        startActivity(studentsListIntent);
    }

}