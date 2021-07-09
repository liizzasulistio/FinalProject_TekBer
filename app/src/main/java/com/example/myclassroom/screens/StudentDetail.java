package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;

public class StudentDetail extends AppCompatActivity {

    private TextView student;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);
        student = (TextView)findViewById(R.id.name_txt);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            String studentName = (String)bundle.get("student");
            student.setText(studentName);
        }
    }
}
