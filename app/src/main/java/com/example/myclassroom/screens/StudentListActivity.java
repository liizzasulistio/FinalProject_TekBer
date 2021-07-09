package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.data.DummyData;
import com.example.myclassroom.data.StudentsData;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView studentListRV;
    StudentAdapter studentAdapter;
    List<StudentsData.StudentsDummy> mData;
    private String classID;
//    String sessionId = getIntent().getStringExtra("EXTRA_ID_KELAS");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
//        setSupportActionBar();
        setupItemView();
//        TextView token = findViewById(R.id.classToken);
//        token =
//        if(getIntent().getStringExtra("EXTRA_ID_KELAS") != null)
//        {
//            String value = getIntent().getStringExtra("EXTRA_ID_KELAS");
//            token.setText(value);
//        }
//        setupView();
         classID = getIntent().getStringExtra("EXTRA_ID_KELAS");
    }

    private void setupItemView() {

        studentListRV = findViewById(R.id.studentListRV);
        mData = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, mData);
        studentListRV.setAdapter(studentAdapter);
        studentListRV.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    public void loadData() {

        studentAdapter = new StudentAdapter(this,mData);
        studentListRV.setAdapter(studentAdapter);
        studentListRV.setLayoutManager(new LinearLayoutManager(this));
//
//        studentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(StudentListActivity.this, StudentDetail.class);
//                intent.putExtra("student", mData.get(position).getStudentName());
//                Toast.makeText(StudentListActivity.this, mData.get(position).getStudentName().toString(),Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        });
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("idk", "Class Id:" + classID);
        db.collection("classroom")
                .document(classID)
                .get()
                .continueWithTask((Continuation<DocumentSnapshot, Task<List<DocumentSnapshot>>>) task -> {
                    List<Task<DocumentSnapshot>> tasks = new ArrayList<>();
                    DocumentSnapshot ds = task.getResult();
                    Log.d("idk", "Document ID : " + ds.getId());
                    ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) ds.get("students");
                    Log.d("idk", students == null ? "exits": "not");
                    for (Map<String, Object> m : students){
                        Log.d("idk", "Search ID: "+ ((DocumentReference)m.get("user_id")).getId());
                        Log.d("idk", ((DocumentReference)m.get("user_id")).getId());
                        tasks.add(db.collection("user").document(((DocumentReference)m.get("user_id")).getId()).get());
                    }
                    return Tasks.whenAllSuccess(tasks);
                }).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        List<DocumentSnapshot> studentDataList = task.getResult();
                        List<StudentsData.StudentsDummy> studentList = new ArrayList<>();
                        for (DocumentSnapshot ds : studentDataList) {
                            Map<String, Object> data = ds.getData();
                            studentList.add(new StudentsData.StudentsDummy(ds.getId(), classID, data.get("name").toString(), data.get("nrp").toString()));
                        }
                        studentAdapter = new StudentAdapter(getApplicationContext(),studentList);
                        studentListRV.setAdapter(studentAdapter);
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