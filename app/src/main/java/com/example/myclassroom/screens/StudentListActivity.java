package com.example.myclassroom.screens;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclassroom.R;
import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.data.StudentsData;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView studentListRV;
    StudentAdapter studentAdapter;
    List<StudentsData.StudentsDummy> mData;
    TextView token;
    String value = getIntent().getStringExtra("EXTRA_ID_KELAS");
//    String sessionId = getIntent().getStringExtra("EXTRA_ID_KELAS");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
//        setSupportActionBar();
//        setupItemView();
//        token = findViewById(R.id.classToken);

        if(getIntent().getStringExtra("EXTRA_ID_KELAS") != null)
        {
            value = getIntent().getStringExtra("EXTRA_ID_KELAS");
//            token.setText(value);
        }
////        loadData();
//        setupView();
    }

    private void setupItemView() {

//        studentListRV = findViewById(R.id.studentListRV);
        mData = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, mData);
        studentListRV.setAdapter(studentAdapter);
        studentListRV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupView() {

    }

    public void loadData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("idk", "current user: " + curUser.getUid());
        List<StudentsData.StudentsDummy> nData = new ArrayList<>();
        db.collection("classroom")
//                .whereArrayContains("students", curUser.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            String id = documentSnapshot.getId();
                            if (id == value) {
                                Map<String, Object> datas = documentSnapshot.getData();
                                ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) documentSnapshot.get("students");
                                boolean isStudent = false;
                                if (students != null) {
                                    Log.d("idk", String.valueOf(students.size()));


                                    for (Map<String, Object> m : students) {
                                        DocumentReference docRef = (DocumentReference) m.get("user_id");
                                        if (docRef != null) {
                                            Log.d("idk", "User Found : " + docRef.getId());
                                            if (docRef.getId().equals(curUser.getUid())) {
                                                Log.d("idk", "User Match");
                                                isStudent = true;
                                                break;
                                            }
                                        }
                                    }
                                }

                                if (((DocumentReference) datas.get("owner_id")).getId().equals(curUser.getUid())) {
                                    isStudent = true;
                                }
                                if (!isStudent) {
                                    Log.d("idk", "Skipped making classroom model");
                                    continue;
                                }
                                StudentsData.StudentsDummy newClass = new StudentsData.StudentsDummy(
                                        id,
                                        datas.get("ava").toString(),
                                        datas.get("name").toString(),
                                        datas.get("nrp").toString(),
                                        ((DocumentReference) datas.get("owner_id")).getId()
                                );
                                nData.add(newClass);
                            }

                            StudentAdapter newStudentAdapter = new StudentAdapter(getApplicationContext(), nData);
                            studentListRV.swapAdapter(newStudentAdapter, true);
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Failed to Connect to Firestore", Toast.LENGTH_SHORT);
                    }
                });







    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}