package com.example.myclassroom.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

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

//        Log.d("testing : " , classID);
//        Log.d("testing : " , classID);
//        Log.d("testing : " , classID);
        if (getIntent().getStringExtra("student") != null) {
            id = getIntent().getStringExtra("studentID");
            classID = getIntent().getStringExtra("classID");
            Log.d("idk", "Class ID:" + classID);
            value = getIntent().getStringExtra("student");
            studentName.setText(value);
            String val2 = getIntent().getStringExtra("student_nrp");
            studentNRP.setText(val2);
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
                        studentScore.setText(grade.toString());
                    }
                });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("classroom").document(classID).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot ds = task.getResult();
                                    Map<String, Object> data = ds.getData();
                                    ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) data.get("students");
                                    for (Map<String, Object> student: students){
                                        DocumentReference dr = (DocumentReference) student.get("user_id");
                                        if(id.equals(dr.getId())){
                                            student.put("grade", Long.valueOf(studentScore.getText().toString()));
                                            break;
                                        }
                                    }
                                    db.collection("classroom").document(classID).set(data);
                                    finish();
                                }
                            }
                        });
            }
        });


    }
}
