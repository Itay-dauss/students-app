package com.example.assignment2_students_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Students App");

        Button seeStudentsBtn = findViewById(R.id.see_students_button);
        seeStudentsBtn.setOnClickListener(view -> {
            Intent studentsListIntent = new Intent(MainActivity.this, StudentsListActivity.class);
            startActivity(studentsListIntent);
        });
    }
}