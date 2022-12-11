package com.example.assignment2_students_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2_students_app.model.Model;
import com.example.assignment2_students_app.model.Student;

public class EditStudentActivity extends AppCompatActivity {
    Student student;
    int studentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_students);
        getSupportActionBar().setTitle("Edit Student");

        student = getIntent().getParcelableExtra("studentDetails");
        studentIndex = getIntent().getIntExtra("studentIndex", -1);
        setStudentData(student);

        EditText namePt = findViewById(R.id.edit_student_name_pt);
        EditText idPt = findViewById(R.id.edit_student_id_pt);
        EditText phonePt = findViewById(R.id.edit_student_phone_pt);
        EditText addressPt = findViewById(R.id.edit_student_address_pt);
        CheckBox checkedCb = findViewById(R.id.edit_student_checked_cb_enabled);

        Button saveEditStudentBtn = findViewById(R.id.edit_student_save_button);
        Button deleteStudentBtn = findViewById(R.id.edit_student_delete_button);
        Button cancelEditStudentBtn = findViewById(R.id.edit_student_cancel_button);

        cancelEditStudentBtn.setOnClickListener(view -> {
            Intent studentDetailsIntent = new Intent(EditStudentActivity.this, StudentDetailsActivity.class);
            studentDetailsIntent.putExtra("studentDetails", student);
            studentDetailsIntent.putExtra("studentIndex", studentIndex);
            startActivity(studentDetailsIntent);
        });

        deleteStudentBtn.setOnClickListener(view -> {
            Model.getInstance().deleteStudent(studentIndex);
            Intent studentsListIntent = new Intent(EditStudentActivity.this, StudentsListActivity.class);
            startActivity(studentsListIntent);
        });

        saveEditStudentBtn.setOnClickListener(view -> {
            Student updatedStudent = new Student(namePt.getText().toString(), idPt.getText().toString(), Long.parseLong(phonePt.getText().toString()), addressPt.getText().toString(), "",checkedCb.isChecked());
            Model.getInstance().editStudent(studentIndex, updatedStudent);
            Intent studentDetailsIntent = new Intent(EditStudentActivity.this, StudentDetailsActivity.class);
            studentDetailsIntent.putExtra("studentDetails", updatedStudent);
            studentDetailsIntent.putExtra("studentIndex", studentIndex);
            startActivity(studentDetailsIntent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void setStudentData(Student student) {
        TextView nameTv = findViewById(R.id.edit_student_name_pt);
        TextView idTv = findViewById(R.id.edit_student_id_pt);
        TextView phoneTv = findViewById(R.id.edit_student_phone_pt);
        TextView addressTv = findViewById(R.id.edit_student_address_pt);
        CheckBox checkedCb = findViewById(R.id.edit_student_checked_cb_enabled);

        nameTv.setText(student.name);
        idTv.setText(student.id);
        phoneTv.setText(Long.toString(student.phone));
        addressTv.setText(student.address);
        checkedCb.setChecked(student.cb);
    }

    @Override
    public void onBackPressed() {
        Intent studentDetailsIntent = new Intent(EditStudentActivity.this, StudentDetailsActivity.class);
        studentDetailsIntent.putExtra("studentDetails", student);
        studentDetailsIntent.putExtra("studentIndex", studentIndex);
        startActivity(studentDetailsIntent);
    }
}