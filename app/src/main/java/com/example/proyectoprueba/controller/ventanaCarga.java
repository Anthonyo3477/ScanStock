package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.auth.FirebaseAuth;

public class ventanaCarga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_carga);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(ventanaCarga.this, Home.class));
                finish();

                finish();
            }
        }, 1500);
    }
}