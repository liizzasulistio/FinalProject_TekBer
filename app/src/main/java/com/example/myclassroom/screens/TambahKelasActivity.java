package com.example.myclassroom.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.data.DummyData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firestore.v1.WriteResult;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TambahKelasActivity extends AppCompatActivity {

    RecyclerView KelasRecyclerView;
    AdapterKelas kelasAdapter;
    List<DummyData.DataKelas> mData;
    private EditText etTambahKelas;
    private Button btnTambahKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);

        setupItemWiew();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setupItemWiew() {

        etTambahKelas = findViewById(R.id.et_tambah_kelas);
        btnTambahKelas = findViewById(R.id.btn_tambah_kelas);

        KelasRecyclerView = findViewById(R.id.rv_kelas_sendiri);
        mData = new ArrayList<>();
        kelasAdapter = new AdapterKelas(this,mData);
        KelasRecyclerView.setAdapter(kelasAdapter);
        KelasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        kelasAdapter.setOnItemClickListener(new AdapterKelas.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Intent intent = new Intent(ListKelasActivity.this, StudentListActivity.class);
//                intent.putExtra("EXTRA_ID_KELAS", mData.get(position).getId().toString());
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
//                Toast.makeText(ListKelasActivity.this, mData.get(position).getNama_kelas().toString(),
//                        Toast.LENGTH_SHORT).show();
//                startActivity(intent);
            }
        });

        btnTambahKelas.setOnClickListener(tambahKelas);
    }

    public static String generateRandomPassword(int len) {
        String chars = "23456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    private View.OnClickListener tambahKelas = v -> {
        Intent intent = new Intent(TambahKelasActivity.this, TambahKelasActivity.class);

        String namaKelas = etTambahKelas.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();

        // fetch reference database
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mDbRef = mDatabase.getReference("user");

        DocumentReference ref = db.collection("user").document(curUser.getUid());

        String token = generateRandomPassword(6);

         Map<String, Object> addClassrroom = new HashMap<>();
         addClassrroom.put("name", namaKelas);
         addClassrroom.put("owner_id", ref);
         addClassrroom.put("token", token);

//         for ( Map<String, Object> oldData: oldClassroomStudents ) {
//              // iterasi in data student lama
//              DocumentReference docRef = (DocumentReference) m.get("user_id");
//              if (docRef != null) {
//                  Log.d("idk", "User Found : " + docRef.getId());
//                  if(docRef.getId().equals(YANG_DICARI)){
//                      m.put("grade", 100);
//                      break;
//                  }
//              }
//         }
//         newClassroom.put("students", oldClassroomStudents)
            db.collection("classroom").add(addClassrroom);
//         System.out.println("Update time : " + db.get().getUpdateTime());
        etTambahKelas.getText().clear();
        kelasAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), "Kelas berhasil di tambahkan", Toast.LENGTH_SHORT).show();
    };

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

    public void loadData() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("idk", "current user: " + curUser.getUid());
        List<DummyData.DataKelas> nData = new ArrayList<>();
        db.collection("classroom")
//                .whereArrayContains("students", curUser.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            String id = documentSnapshot.getId();
                            Map<String, Object> datas = documentSnapshot.getData();
//                            ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) documentSnapshot.get("students");
//                            Log.d("idk", String.valueOf(students.size()));
                            boolean isOwner = false;
                            if(((DocumentReference)datas.get("owner_id")).getId().equals(curUser.getUid())){
                                isOwner = true;
                            }
                            if (!isOwner) {
                                Log.d("idk", "Skipped making classroom model");
                                continue;
                            }
                            DummyData.DataKelas newClass = new DummyData.DataKelas(
                                    id,
                                    datas.get("name").toString(),
                                    datas.get("token").toString(),
                                    ((DocumentReference)datas.get("owner_id")).getId()
                            );
                            nData.add(newClass);
                        }
                        AdapterKelas newKelasAdapter = new AdapterKelas(getApplicationContext(), nData);
                        KelasRecyclerView.swapAdapter(newKelasAdapter, true);
                    }else {
                        Toast.makeText(getApplicationContext(), "Failed to Connect to Firestore", Toast.LENGTH_SHORT);
                    }
                });


        // add / update new classroom
        // Map<String, Object> newClassroom = new Map<String, Object>();
        // newClassroom.put("name", NAMA_KELAS);
        // DocumentReference owner = db.collection("user).document(OWNER_ID);
        // newClassroom.put("owner_id", owner);
        // newClassroom.put("token", TOKEN);
        // for ( Map<String, Object> oldData: oldClassroomStudents ) {
        //      // iterasi in data student lama
        //      DocumentReference docRef = (DocumentReference) m.get("user_id");
        //      if (docRef != null) {
        //          Log.d("idk", "User Found : " + docRef.getId());
        //          if(docRef.getId().equals(YANG_DICARI)){
        //              m.put("grade", 100);
        //              break;
        //          }
        //      }
        // }
        // newClassroom.put("students", oldClassroomStudents)
        // db.collection("classroom").document(ID_CLASSROOM).set(newClassroom);
    }
}