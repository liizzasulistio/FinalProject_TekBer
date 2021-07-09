package com.example.myclassroom.screens;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;

public class EditProfileActivity extends AppCompatActivity {

    EditText studentName, studentNRP, studentEmail, studentPassword;
    TextView studentID;
    String id;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        studentName = (EditText) findViewById(R.id.edtName);
        studentNRP = (EditText) findViewById(R.id.edtNRP);
        studentEmail = (EditText) findViewById(R.id.edtEmail);
        studentPassword = (EditText) findViewById(R.id.edtPwd);

        studentID = (TextView) findViewById(R.id.studentID);

        if (getIntent().getStringExtra("userID") != null) {
            id = getIntent().getStringExtra("userID");
            studentID.setText(id);
        }
    }
}
