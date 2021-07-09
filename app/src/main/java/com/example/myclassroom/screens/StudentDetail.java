package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;

public class StudentDetail extends AppCompatActivity {

    private TextView student, nrp, nilai_angka, nilai_huruf;
    private Button btnEdit;
    private String value, val2, id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);
        student = (TextView) findViewById(R.id.studentName);
        nrp = (TextView) findViewById(R.id.studentNRP);
        btnEdit = (Button) findViewById(R.id.btnEditStudent);
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();

        if (getIntent().getStringExtra("student") != null) {
            id = getIntent().getStringExtra("studentID");
            value = getIntent().getStringExtra("student");
            student.setText(value);
            val2 = getIntent().getStringExtra("student_nrp");
            nrp.setText(val2);
        }


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDetail.this, EditStudentActivity.class);
                intent.putExtra("studentID", id);
                intent.putExtra("student", value);
                intent.putExtra("student_nrp", val2);
                startActivity(intent);
            }
        });
    }
}
