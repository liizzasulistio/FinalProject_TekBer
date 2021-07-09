package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class StudentDetail extends AppCompatActivity {

    private TextView student, nrp, nilai_angka, nilai_huruf;
    private Button btnEdit;
    private String value, val2, id, classID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_detail);
        student = (TextView) findViewById(R.id.studentName);
        nrp = (TextView) findViewById(R.id.studentNRP);
        btnEdit = (Button) findViewById(R.id.btnEditStudent);
        nilai_angka = (TextView) findViewById(R.id.tv_nilai_angka);

        Log.d("students" , getIntent().getStringExtra("classID") );
        if (getIntent().getStringExtra("student") != null) {
            classID = getIntent().getStringExtra("classID");
            id = getIntent().getStringExtra("studentID");
            value = getIntent().getStringExtra("student");
            student.setText(value);
            val2 = getIntent().getStringExtra("student_nrp");
            nrp.setText(val2);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("classroom").document(classID).get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        DocumentSnapshot ds = task.getResult();
                        ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) ds.get("students");
                        Number grade = 0;
                        for (Map<String, Object> student: students){
                            DocumentReference dr = (DocumentReference) student.get("user_id");
                            if(id.equals(dr.getId())){
                                grade = (Number) student.get("grades");
                                break;
                            }
                        }
                        nilai_angka.setText(grade.toString());
                    }
                });

//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDetail.this, EditStudentActivity.class);
                intent.putExtra("studentID", id);
                intent.putExtra("student", value);
                intent.putExtra("student_nrp", val2);
                intent.putExtra("classID", classID);
                startActivity(intent);
            }
        });
    }
}
