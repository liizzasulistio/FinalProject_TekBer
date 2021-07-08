package com.example.myclassroom.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myclassroom.R;

public class TambahKelasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
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
            return true;
        }
        if (id == R.id.nav_profile) {
            return true;
        }
        if (id == R.id.nav_masuk_kelas) {
            return true;
        }
        if (id == R.id.nav_tambah_kelas) {
            return true;
        }
        if (id == R.id.nav_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}