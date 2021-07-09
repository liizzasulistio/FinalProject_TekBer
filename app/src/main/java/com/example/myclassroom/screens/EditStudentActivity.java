package com.example.myclassroom.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;

public class EditStudentActivity extends AppCompatActivity
{
    TextView studentName, studentNRP;
    EditText studentScore;
    private String value, id, classID;
    private Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_student);

        studentName = (TextView) findViewById(R.id.studentName);
        studentNRP = (TextView) findViewById(R.id.studentNRP);
        studentScore = (EditText) findViewById(R.id.edtNilai);
        btnSave = (Button) findViewById(R.id.btnSave);


        if (getIntent().getStringExtra("student") != null) {
            id = getIntent().getStringExtra("studentID");
            classID = getIntent().getStringExtra("classID");
            value = getIntent().getStringExtra("student");
            studentName.setText(value);
            String val2 = getIntent().getStringExtra("student_nrp");
            studentNRP.setText(val2);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
