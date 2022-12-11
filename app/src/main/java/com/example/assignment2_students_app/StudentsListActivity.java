package com.example.assignment2_students_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignment2_students_app.model.Model;
import com.example.assignment2_students_app.model.Student;

import java.util.List;

public class StudentsListActivity extends AppCompatActivity {
    List<Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        getSupportActionBar().setTitle("Students List");

        studentList = Model.getInstance().getAllStudents();

        RecyclerView studentsList = findViewById(R.id.studentslist_list);
        studentsList.setHasFixedSize(true);
        studentsList.setLayoutManager(new LinearLayoutManager(this));
        StudentsListAdapter adapter = new StudentsListAdapter();
        studentsList.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Student clickedStudent = studentList.get(position);
                Intent studentDetailsIntent = new Intent(StudentsListActivity.this, StudentDetailsActivity.class);
                studentDetailsIntent.putExtra("studentDetails", clickedStudent);
                studentDetailsIntent.putExtra("studentIndex", position);
                startActivity(studentDetailsIntent);
            }
        });

        Button addStudentBtn = findViewById(R.id.add_student_button);

        addStudentBtn.setOnClickListener(view -> {
            Intent newStudentIntent = new Intent(StudentsListActivity.this, NewStudentActivity.class);
            startActivity(newStudentIntent);
        });
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) cb.getTag();
                    Student student = studentList.get(pos);
                    student.cb = cb.isChecked();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onItemClick(position);
                }
            });
        }

        public void bind(Student student, int position) {
            nameTv.setText(student.name);
            idTv.setText(student.id);
            cb.setChecked(student.cb);
            cb.setTag(position);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class StudentsListAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate((R.layout.student_list_row), parent,false);
            return new StudentViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student student = studentList.get(position);
            holder.bind(student,position);
        }

        @Override
        public int getItemCount() {
            return studentList.size();
        }
    }

    @Override
    public void onBackPressed() {
        Intent MainScreenIntent = new Intent(StudentsListActivity.this, MainActivity.class);
        startActivity(MainScreenIntent);
    }

}