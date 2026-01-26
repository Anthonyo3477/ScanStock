package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class Home extends AppCompatActivity {

    private ProgressBar progressHome;
    private Button btnIrMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnIrMenu = findViewById(R.id.btnIrMenu);
        progressHome = findViewById(R.id.progress_home);

        btnIrMenu.setOnClickListener(v -> {

            progressHome.setVisibility(View.VISIBLE);
            btnIrMenu.setEnabled(false);

            Intent intent = new Intent(Home.this, Login.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressHome.setVisibility(View.GONE);
        btnIrMenu.setEnabled(true);
    }
}