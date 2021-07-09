package com.example.myclassroom.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText studentName, studentNRP, studentEmail, studentPassword;
    TextView studentID;
    String id;
    Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        studentName = (EditText) findViewById(R.id.edtName);
        studentNRP = (EditText) findViewById(R.id.edtNRP);
//        studentEmail = (EditText) findViewById(R.id.edtEmail);
//        studentPassword = (EditText) findViewById(R.id.edtPwd);
        btnSave = (Button) findViewById(R.id.btnSave);

        studentID = (TextView) findViewById(R.id.studentID);

        if (getIntent().getStringExtra("userID") != null) {
            id = getIntent().getStringExtra("userID");
            studentID.setText(id);
        }
        FirebaseFirestore.getInstance().collection("user").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot student = task.getResult();
                            studentName.setText(student.get("name").toString());
                            studentNRP.setText(student.get("nrp").toString());
                        }
                    }
                });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> student = new HashMap<>();
                student.put("name", studentName.getText().toString());
                student.put("nrp", studentNRP.getText().toString());
                FirebaseFirestore.getInstance().collection("user").document(id)
                        .set(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });
            }
        });
    }
}
