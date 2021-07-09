package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;

public class StudentDetail extends AppCompatActivity {

    private TextView student, nrp, nilai_angka, nilai_huruf;
    private Button btnEdit;

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
            String value = getIntent().getStringExtra("student");
            student.setText(value);
            String val2 = getIntent().getStringExtra("student_nrp");
            nrp.setText(val2);
        }
    }
}
