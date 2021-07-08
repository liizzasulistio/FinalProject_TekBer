package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myclassroom.R;
//import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.data.DummyData;
import com.example.myclassroom.data.StudentsData;
import com.example.myclassroom.databinding.ActivityStudentListBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
//
//public class StudentListActivity extends AppCompatActivity {
//
//    private ActivityStudentListBinding binding;
//    private StudentAdapter adapter;
//    private ArrayList<StudentsData> studentList;
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
////        binding = ActivityStudentListBinding.inflate(getLayoutInflater());
////        setContentView(binding.getRoot());
//
//        setContentView(R.layout.activity_student_list);
//
//
////        binding.studentListRV.setAdapter(adapter);
//        initStudentList();
//        setupRecyclerView();
//
//    }
//
//    private void setupRecyclerView()
//    {
//        recyclerView = findViewById(R.id.studentListRV);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        adapter = new StudentAdapter(studentList);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
////        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
////            @Override
////            public void onItemClick(int position) {
////                Intent intent = new Intent(StudentListActivity.this, StudentDetail.class);
////                intent.putExtra("Student", studentList.get(position));
////                startActivity(intent);
////            }
////        });
//
//
////        binding.studentListRV.setHasFixedSize(true);
////        layoutManager = new LinearLayoutManager(this);
////        adapter.(studentList);
////
////
////        binding.studentListRV.setLayoutManager(layoutManager);
////        binding.studentListRV.setAdapter(adapter);
//
////        binding.studentListRV.setOnClickListener(new StudentAdapter.OnItemClickListener())
////        {
////            @Override
////            public void onItemClick(int position)
////            {
////                Intent intent = new Intent(StudentListActivity.this, )
////            }
////        }
//    }
//
//
//    private void initStudentList()
//    {
////        List<String> studentList = new ArrayList<>();
//        studentList = new ArrayList<>();
//        studentList.add(new StudentsData(1,1, "Hana", "1234"));
//        studentList.add(new StudentsData(2,2, "Hani", "5672"));
//        studentList.add(new StudentsData(3, 3, "Hans", "4362"));
//
//    }
//
//}




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
        studentsDummyList.add(new StudentsData.StudentsDummy(1,1,"Hana","123"));
        studentsDummyList.add(new StudentsData.StudentsDummy(2,3,"Hani","456"));
        studentsDummyList.add(new StudentsData.StudentsDummy(2,3,"Hans","789"));

        studentAdapter = new StudentAdapter(this,studentsDummyList);
        studentListRV.setAdapter(studentAdapter);
        studentListRV.setLayoutManager(new LinearLayoutManager(this));
    }

    

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}