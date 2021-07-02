package com.example.myclassroom.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myclassroom.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        setupItemWiew();
        setupView();
    }

    private void setupItemWiew() {
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login_masuk);
        tvSignUp = findViewById(R.id.tv_login_daftar);
//        ProgressBar pbLoading = findViewById(R.id.loading);
    }

    private void setupView() {
        btnLogin.setOnClickListener(masuk);
        tvSignUp.setOnClickListener(regis);
    }

    private View.OnClickListener masuk = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, ListKelasActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener regis = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        }
    };
}