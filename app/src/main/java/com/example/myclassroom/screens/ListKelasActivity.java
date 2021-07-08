package com.example.myclassroom.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.data.DummyData;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class ListKelasActivity extends AppCompatActivity  {

    RecyclerView KelasRecyclerView;
    AdapterKelas kelasAdapter;
    List<DummyData.DataKelas> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kelas);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.nav_list_kelas){
            Intent intent = new Intent(this, ListKelasActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_tambah_kelas) {
            Intent intent = new Intent(this, TambahKelasActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_profile) {
        }
        if (id == R.id.nav_logout) {
        }

        return super.onOptionsItemSelected(item);
    }

}