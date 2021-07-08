package com.example.myclassroom.screens;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myclassroom.R;
import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.data.StudentsData;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class StudentListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView studentListRV;
    StudentAdapter studentAdapter;
    List<StudentsData.StudentsDummy> studentsDummyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
//        setSupportActionBar();
        setupItemView();
        loadData();
        setupView();
    }

    private void setupItemView() {

        studentListRV = findViewById(R.id.studentListRV);
        studentsDummyList = new ArrayList<>();
    }

    private void setupView() {

    }

    public void loadData() {
//        studentsDummyList.add(new StudentsData.StudentsDummy(1,1,"Hana","123"));
//        studentsDummyList.add(new StudentsData.StudentsDummy(2,3,"Hani","456"));
//        studentsDummyList.add(new StudentsData.StudentsDummy(2,3,"Hans","789"));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("classroom").document();


        studentAdapter = new StudentAdapter(this,studentsDummyList);
        studentListRV.setAdapter(studentAdapter);
        studentListRV.setLayoutManager(new LinearLayoutManager(this));
    }

//    public List<StudentsData.StudentsDummy>

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}