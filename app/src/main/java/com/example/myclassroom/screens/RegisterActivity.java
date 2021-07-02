package com.example.myclassroom.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myclassroom.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etNama;
    private EditText etNrp;
    private EditText etPassword;
    private Button btnRegis;
    private TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        setupItemWiew();
        setupView();
    }

    private void setupItemWiew() {
        etEmail = findViewById(R.id.et_regis_email);
        etNama = findViewById(R.id.et_regis_Name);
        etNrp = findViewById(R.id.et_regis_nrp);
        etPassword = findViewById(R.id.et_login_password);
        btnRegis = findViewById(R.id.btn_regis_daftar);
        tvSignIn = findViewById(R.id.tv_daftar_login);

//        ProgressBar pbLoading = findViewById(R.id.loading);
    }

    private void setupView() {
        btnRegis.setOnClickListener(daftar);
        tvSignIn.setOnClickListener(login);
    }

    private View.OnClickListener daftar = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, ListKelasActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener login = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };
}