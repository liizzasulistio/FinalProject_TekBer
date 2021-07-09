package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyProfileActivity extends AppCompatActivity {

    TextView NamaSiswa,NrpSiswa,EmailSiswa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

        NamaSiswa = findViewById(R.id.tv_nama_siswa);
        NrpSiswa = findViewById(R.id.tv_nama_siswa);
        EmailSiswa = findViewById(R.id.tv_email_siswa);

        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();

//        NamaSiswa.setText(curUser.get("name"));
        EmailSiswa.setText(curUser.getEmail());
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
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ListKelasActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_tambah_kelas) {
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TambahKelasActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_profile) {
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MyProfileActivity.class);
            startActivity(intent);
            finish();
        }
        if (id == R.id.nav_logout) {
//            Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
