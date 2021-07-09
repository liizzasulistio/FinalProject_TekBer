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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
import com.example.myclassroom.adapter.StudentAdapter;
import com.example.myclassroom.data.DummyData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListKelasActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView KelasRecyclerView;
    AdapterKelas kelasAdapter;
    List<DummyData.DataKelas> mData;

    private EditText etMasukKelas;
    private Button btnMasukKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kelas);

        setupItemWiew();

        loadData();
        setupView();
    }

    private void setupItemWiew() {

        etMasukKelas = findViewById(R.id.et_masuk_kelas);
        btnMasukKelas = findViewById(R.id.btn_masuk_kelas);

        KelasRecyclerView = findViewById(R.id.rv_kelas);
        mData = new ArrayList<>();
        kelasAdapter = new AdapterKelas(this,mData);
        KelasRecyclerView.setAdapter(kelasAdapter);
        KelasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set up adapter and pass clicked listener this

//        kelasAdapter.setOnItemClickListener(new AdapterKelas.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//
////                Toast.makeText(ListKelasActivity.this, Log.e(mData.get(position).getNama_kelas()) ,Toast.LENGTH_SHORT).show();
//                Log.e("nama kelas",mData.get(position).getNama_kelas());
////                FirebaseFirestore db = FirebaseFirestore.getInstance();
////                db.collection("classroom").document(ID_CLASSROOM)
////                Intent intent = new Intent(ListKelasActivity.this, StudentListActivity.class);
////                intent.putExtra("EXTRA_ID_KELAS", mData.get(position).getId());
////
//
////                startActivity(intent);
//
////                System.out.println("position : " + mData.get(position).getNama_kelas());
//            }
//        });

        btnMasukKelas.setOnClickListener(masukKelas);

    }

    private View.OnClickListener masukKelas = v -> {

//        Intent intent = new Intent(TambahKelasActivity.this, TambahKelasActivity.class);
//
//        String tokenKelas = etMasukKelas.getText().toString();
//
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        DocumentReference ref = db.collection("user").document(curUser.getUid());
//
//
//        Map<String, Object> addClassrroom = new HashMap<>();
//        addClassrroom.put("grades", 0);
//        addClassrroom.put("user_id", ref);
////
//        List<DummyData.DataKelas> nData = new ArrayList<>();
//        db.collection("classroom")
////                .whereArrayContains("students", curUser.getUid())
//                .get()
//                .addOnCompleteListener(task -> {
//                    if(task.isSuccessful()) {
//                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                            String id = documentSnapshot.getId();
//                            Map<String, Object> datas = documentSnapshot.getData();
////                            ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) documentSnapshot.get("students");
////                            Log.d("idk", String.valueOf(students.size()));
//
//
//                            DummyData.DataKelas newClass = new DummyData.DataKelas(
//                                    id,
//                                    datas.get("name").toString(),
//                                    datas.get("token").toString(),
//                                    ((DocumentReference)datas.get("owner_id")).getId()
//                            );
//                            nData.add(newClass);
//                        }
//                        AdapterKelas newKelasAdapter = new AdapterKelas(getApplicationContext(), nData);
//                        KelasRecyclerView.swapAdapter(newKelasAdapter, true);
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Failed to Connect to Firestore", Toast.LENGTH_SHORT);
//                    }
//                });


//        Testing

//        Intent intent = new Intent(TambahKelasActivity.this, TambahKelasActivity.class);

//        String tokenKelas = etMasukKelas.getText().toString();
//
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser curUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        DocumentReference ref = db.collection("user").document(curUser.getUid());
//
//
//        Map<String, Object> addClassrroom = new HashMap<>();
//        addClassrroom.put("grades", 0);
//        addClassrroom.put("user_id", ref);

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

//        db.collection("classroom").add(addClassrroom);
//
//        etMasukKelas.getText().clear();
//        kelasAdapter.notifyDataSetChanged();
//        Toast.makeText(getApplicationContext(), "Anda Berhasil Masuk Kelas", Toast.LENGTH_SHORT).show();
    };

    private void setupView() {

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
                            ArrayList<Map<String, Object>> students = (ArrayList<Map<String, Object>>) documentSnapshot.get("students");
                            boolean isStudent = false;
                            if( students != null){
                                Log.d("idk", String.valueOf(students.size()));


                                for (Map<String, Object> m : students){
                                    DocumentReference docRef = (DocumentReference) m.get("user_id");
                                    if (docRef != null) {
                                        Log.d("idk", "User Found : " + docRef.getId());
                                        if(docRef.getId().equals(curUser.getUid())){
                                            Log.d("idk", "User Match");
                                            isStudent = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if(((DocumentReference)datas.get("owner_id")).getId().equals(curUser.getUid())){
                                isStudent = true;
                            }
                            if (!isStudent) {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}