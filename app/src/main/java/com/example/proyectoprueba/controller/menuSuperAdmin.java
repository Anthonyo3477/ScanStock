package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoprueba.R;

public class menuSuperAdmin extends AppCompatActivity {

    private Button btnAvisos, btnAgregarAviso, btngestionUsuarios, btnVolver, btnGenerarReporte, btnAgregarPersonal;
    private ProgressBar progressMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_super_admin);

        btnAvisos = findViewById(R.id.btnAvisos);
        btnAgregarAviso = findViewById(R.id.btnAgregarAviso);
        btngestionUsuarios = findViewById(R.id.btngestionUsuarios);
        btnGenerarReporte = findViewById(R.id.btnGenerarReporte);
        btnAgregarPersonal = findViewById(R.id.btnAgregarPersonal);
        btnVolver = findViewById(R.id.btnVolver);
        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Avisos
        btnAvisos.setOnClickListener(v ->{
            navegar(new Intent(this, Avisos.class));
        });

        // Ir a Agregar Aviso
        btnAgregarAviso.setOnClickListener( v ->{
            navegar(new Intent(this, agregarAviso.class));
        });

        // Ir a Agregar Personal
        btnAgregarPersonal.setOnClickListener( v -> {
            navegar(new Intent(this, registrarPersonal.class));
        });

        // Ir a Gestion de Usuarios
        btngestionUsuarios.setOnClickListener( v -> {
            navegar(new Intent(this, gestionUsuarios.class));
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
        btnVolver.setOnClickListener( v ->{
            finish();
        });
    }

    private void navegar(Intent intent){
        progressMenu.setVisibility(View.VISIBLE);

        btnAvisos.setEnabled(false);
        btnAgregarAviso.setEnabled(false);
        btngestionUsuarios.setEnabled(false);
        btnGenerarReporte.setEnabled(false);
        btnAgregarPersonal.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        btnAvisos.setEnabled(true);
        btnAgregarAviso.setEnabled(true);
        btngestionUsuarios.setEnabled(true);
        btnGenerarReporte.setEnabled(true);
        btnAgregarPersonal.setEnabled(true);
        btnVolver.setEnabled(true);
    }

}
