package com.example.myclassroom.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myclassroom.R;
import com.example.myclassroom.adapter.AdapterKelas;
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
import java.util.List;
import java.util.Map;

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
        kelasAdapter = new AdapterKelas(this,mData);
        KelasRecyclerView.setAdapter(kelasAdapter);
        KelasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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
                            Log.d("idk", String.valueOf(students.size()));
                            boolean isStudent = false;
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}