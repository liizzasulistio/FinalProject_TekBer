package com.example.myclassroom.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.data.DummyData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class MyProfileActivity extends AppCompatActivity {

    TextView NamaSiswa,NrpSiswa,EmailSiswa;
    Button btnSave;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

        NamaSiswa = findViewById(R.id.tv_nama_siswa);
        NrpSiswa = findViewById(R.id.tv_nrp_siswa);
        EmailSiswa = findViewById(R.id.tv_email_siswa);
        btnSave = findViewById(R.id.btnSave);

        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("user")
//                .whereEqualTo("Uid", curUser.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            String id = documentSnapshot.getId();
                            Map<String, Object> datas = documentSnapshot.getData();
                            if(documentSnapshot.getId().equals(curUser.getUid())){
                                NamaSiswa.setText(documentSnapshot.get("name").toString());
                                NrpSiswa.setText(documentSnapshot.get("nrp").toString());
                            }
                        }

                    }else {
                        Toast.makeText(getApplicationContext(), "Failed to Connect to Firestore", Toast.LENGTH_SHORT);
                    }
                });

        EmailSiswa.setText(curUser.getEmail());

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MyProfileActivity.this, EditProfileActivity.class);
//                intent.putExtra("userID", curUser.getUid());
//                startActivity(intent);
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
//    private void getData(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        final String current = user.getUid();//getting unique user id
//
//        db.collection("user")
//                .whereEqualTo("uId",current)//looks for the corresponding value with the field
//                // in the database
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//
//                                NamaSiswa.setText((CharSequence) document.get("name"));
//                                NrpSiswa.setText((CharSequence) document.get("nrp"));
//                                Log.d("testing",document.get("name").toString());
//                                // These values must exactly match the fields you have in your db
//
//                            }
//                        }}});};

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
