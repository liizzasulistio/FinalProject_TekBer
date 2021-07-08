package com.example.myclassroom.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myclassroom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etNama;
    private EditText etNrp;
    private EditText etPassword;
    private Button btnRegis;
    private TextView tvSignIn;
    private ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        setupItemView();
        setupView();
    }

    private void setupItemView() {
        etEmail = findViewById(R.id.et_regis_email);
        etNama = findViewById(R.id.et_regis_Name);
        etNrp = findViewById(R.id.et_regis_nrp);
        etPassword = findViewById(R.id.et_regis_password);
        btnRegis = findViewById(R.id.btn_regis_daftar);
        tvSignIn = findViewById(R.id.tv_daftar_login);
        pbLoading = findViewById(R.id.loading);
    }

    private void setupView() {
        btnRegis.setOnClickListener(daftar);
        tvSignIn.setOnClickListener(login);
    }

    private View.OnClickListener daftar = v -> {
        Intent intent = new Intent(RegisterActivity.this, ListKelasActivity.class);

        String name = etNama.getText().toString();
        String nrp = etNrp.getText().toString();
        Map<String, String> user = new HashMap<>();
        user.put("name", name);
        user.put("nrp", nrp);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        pbLoading.setVisibility(View.VISIBLE);
        btnRegis.setEnabled(false);
        tvSignIn.setEnabled(false);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    pbLoading.setVisibility(View.GONE);
                    btnRegis.setEnabled(true);
                    tvSignIn.setEnabled(true);
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                        FirebaseUser u = auth.getCurrentUser();
                        db.collection("user").document(u.getUid()).set(user);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    };

    private View.OnClickListener login = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}