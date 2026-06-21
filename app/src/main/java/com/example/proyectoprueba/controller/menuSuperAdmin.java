package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoprueba.R;

public class menuSuperAdmin extends AppCompatActivity {

    private Button btnAlertas, btnHistorial, btnAgregarAviso, btnlistarUsuarios, btnVolver, btnGenerarReporte, btnAgregarPersonal;
    private ProgressBar progressMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_super_admin);

        btnAlertas = findViewById(R.id.btnAlertas);
        btnHistorial = findViewById(R.id.btnHistorial);
        btnAgregarAviso = findViewById(R.id.btnAgregarAviso);
        btnlistarUsuarios = findViewById(R.id.btnlistarUsuarios);
        btnGenerarReporte = findViewById(R.id.btnGenerarReporte);
        btnAgregarPersonal = findViewById(R.id.btnAgregarPersonal);
        btnVolver = findViewById(R.id.btnVolver);
        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Alertas
        btnAlertas.setOnClickListener(v ->{
            navegar(new Intent(this, Notificaciones.class));
        });

        // Ir a Historial de Alertas
        btnHistorial.setOnClickListener(v ->{
            navegar(new Intent(this, HistorialAlertas.class));
        });


        // Ir a Agregar Aviso
        btnAgregarAviso.setOnClickListener( v ->{
            navegar(new Intent(this, agregarAlertas.class));
        });

        // Ir a Agregar Personal
        btnAgregarPersonal.setOnClickListener( v -> {
            navegar(new Intent(this, registrarPersonal.class));
        });

        // Ir a Listar los Usuarios
        btnlistarUsuarios.setOnClickListener( v -> {
            navegar(new Intent(this, listarUsuarios.class));
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

        btnAlertas.setEnabled(false);
        btnAgregarAviso.setEnabled(false);
        btnlistarUsuarios.setEnabled(false);
        btnGenerarReporte.setEnabled(false);
        btnAgregarPersonal.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        btnAlertas.setEnabled(true);
        btnAgregarAviso.setEnabled(true);
        btnlistarUsuarios.setEnabled(true);
        btnGenerarReporte.setEnabled(true);
        btnAgregarPersonal.setEnabled(true);
        btnVolver.setEnabled(true);
    }

}
