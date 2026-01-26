package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class menuAdmin extends AppCompatActivity {

    private Button btnAvisos, btnProductos, btnListarProductos, btnVolver, btnAgregarAviso;
    private ProgressBar progressMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_admin);

        btnAvisos = findViewById(R.id.btnAvisos);
        btnProductos = findViewById(R.id.btnProductos);
        btnListarProductos = findViewById(R.id.btnListarProductos);
        btnAgregarAviso = findViewById(R.id.btnAgregarAviso);
        btnVolver = findViewById(R.id.btnVolver);

        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Avisos
        btnAvisos.setOnClickListener( v ->{
            navegar(new Intent(this, Avisos.class));
        });

        // Agregar Avisos
        btnAgregarAviso.setOnClickListener(v ->{
            navegar(new Intent(this, agregarAviso.class));
        });

        // Ir a Productos
        btnProductos.setOnClickListener(v -> {
            navegar(new Intent(this, registrarPersonal.class));
        });

        // Boton para Listar Productos
        btnListarProductos.setOnClickListener(v -> {
            navegar(new Intent(this, categoriaProductos.class));
        });

        // Botón volver al Home
        btnVolver.setOnClickListener( v ->{
            finish();
        });
    }

    private void navegar(Intent intent){
        progressMenu.setVisibility(View.VISIBLE);

        btnAvisos.setEnabled(false);
        btnProductos.setEnabled(false);
        btnListarProductos.setEnabled(false);
        btnAgregarAviso.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        btnAvisos.setEnabled(true);
        btnProductos.setEnabled(true);
        btnListarProductos.setEnabled(true);
        btnAgregarAviso.setEnabled(true);
        btnVolver.setEnabled(true);

    }
}