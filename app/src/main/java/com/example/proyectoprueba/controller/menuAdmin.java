package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class menuAdmin extends AppCompatActivity {

    private Button btnVolver;

    private CardView cardAlertas, cardHistorial, cardAvisos, cardProductos, cardListarProductos;
    private ProgressBar progressMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_admin);

        cardAlertas = findViewById(R.id.cardAlertas);
        cardHistorial = findViewById(R.id.cardHistorial);
        cardAvisos = findViewById(R.id.cardAvisos);
        cardProductos = findViewById(R.id.cardProductos);
        cardListarProductos = findViewById(R.id.cardListarProductos);

        btnVolver = findViewById(R.id.btnVolver);

        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Notificaciones
        cardAlertas.setOnClickListener(v ->{
            navegar(new Intent(this, Notificaciones.class));
        });

        // Ir a Historial de Alertas
        cardHistorial.setOnClickListener(v ->{
            navegar(new Intent(this, HistorialAlertas.class));
        });

        // Agregar Notificaciones
        cardAvisos.setOnClickListener(v ->{
            navegar(new Intent(this, agregarAlertas.class));
        });

        // Ir a Productos
        cardProductos.setOnClickListener(v -> {
            navegar(new Intent(this, agregarProducto.class));
        });

        // Boton para Listar Productos
        cardListarProductos.setOnClickListener(v -> {
            navegar(new Intent(this, categoriaProductos.class));
        });

        // Botón volver al Home
        btnVolver.setOnClickListener( v ->{
            finish();
        });
    }

    private void navegar(Intent intent){
        progressMenu.setVisibility(View.VISIBLE);

        cardAlertas.setEnabled(false);
        cardHistorial.setEnabled(false);
        cardAvisos.setEnabled(false);
        cardProductos.setEnabled(false);
        cardListarProductos.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        cardAlertas.setEnabled(true);
        cardHistorial.setEnabled(true);
        cardAvisos.setEnabled(true);
        cardProductos.setEnabled(true);
        cardListarProductos.setEnabled(true);
        btnVolver.setEnabled(true);
    }
}