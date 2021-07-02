package com.example.myclassroom.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.data.DummyData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListKelasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView KelasRecyclerView;
    AdapterKelas kelasAdapter;
    List<DummyData.DataKelas> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kelas);
//        setSupportActionBar();
        setupItemWiew();
        loadData();
        setupView();
    }

    private void setupItemWiew() {

        KelasRecyclerView = findViewById(R.id.rv_kelas);
        mData = new ArrayList<>();
    }

    private void setupView() {

    }

    public void loadData() {
        mData.add(new DummyData.DataKelas(1,"Tekber","GHHUU79","1"));
        mData.add(new DummyData.DataKelas(2,"MBD","IIDO5","1"));
        mData.add(new DummyData.DataKelas(3,"TEST","IIDO5","1"));
        mData.add(new DummyData.DataKelas(4,"TES 2","IIDO5","1"));
        mData.add(new DummyData.DataKelas(5,"TEST 3","IIDO5","1"));

        kelasAdapter = new AdapterKelas(this,mData);
        KelasRecyclerView.setAdapter(kelasAdapter);
        KelasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}