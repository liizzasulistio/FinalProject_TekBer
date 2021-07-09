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
//    String sessionId = getIntent().getStringExtra("EXTRA_ID_KELAS");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
//        setSupportActionBar();
        setupItemView();
        TextView token = findViewById(R.id.classToken);
//        token =
        if(getIntent().getStringExtra("EXTRA_ID_KELAS") != null)
        {
            String value = getIntent().getStringExtra("EXTRA_ID_KELAS");
            token.setText(value);
        }
        loadData();
//        setupView();
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

    public void loadData() {
//        mData.add(new StudentsData.StudentsDummy(1,1,"Hana","123"));
//        mData.add(new StudentsData.StudentsDummy(2,3,"Hani","456"));
//        mData.add(new StudentsData.StudentsDummy(2,3,"Hans","789"));

//        studentAdapter = new StudentAdapter(this,mData);
//        studentListRV.setAdapter(studentAdapter);
//        studentListRV.setLayoutManager(new LinearLayoutManager(this));
//
//        studentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(StudentListActivity.this, StudentDetail.class);
////                intent.putExtra("Student", mData.toString());
//                Toast.makeText(StudentListActivity.this, "it works",
//                        Toast.LENGTH_SHORT).show();
//                startActivity(intent);
//            }
//        });


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}