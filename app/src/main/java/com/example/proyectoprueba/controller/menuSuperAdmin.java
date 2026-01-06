package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoprueba.R;

public class menuSuperAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_super_admin);

        Button btnAvisos = findViewById(R.id.btnAvisos);
        Button btnAgregarAviso = findViewById(R.id.btnAgregarAviso);
        Button btngestionUsuarios = findViewById(R.id.btngestionUsuarios);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnGenerarReporte = findViewById(R.id.btnGenerarReporte);
        Button btnAgregarPersonal = findViewById(R.id.btnAgregarPersonal);


        // Ir a Avisos
        btnAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuperAdmin.this, Avisos.class);
                startActivity(intent);
            }
        });
        // Ir a Agregar Aviso
        btnAgregarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuperAdmin.this, agregarAviso.class);
                startActivity(intent);
            }
        });

        // Ir a Agregar Personal
        btnAgregarPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuperAdmin.this, registrarPersonal.class);
                startActivity(intent);
            }
        });

        // Ir a Gestion de Usuarios
        btngestionUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuSuperAdmin.this, gestionUsuarios.class);
                startActivity(intent);
            }
        });

        // Ir a Generar Reporte
        btnGenerarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // crear la ventana de Generar Reporte, esta ventana listara todos los avisos
                //Intent intent = new Intent(menuSuperAdmin.this, GenerarReporte.class);
                //startActivity(intent);
            }
        });

        // Boton para volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
