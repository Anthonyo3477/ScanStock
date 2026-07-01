package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.proyectoprueba.R;

public class menuUsuario extends AppCompatActivity {

    private CardView cardNuevoAviso;
    private CardView cardAlertas;
    private Button btnVolver;
    private ProgressBar progressMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario);

        cardNuevoAviso = findViewById(R.id.cardNuevoAviso);
        cardAlertas = findViewById(R.id.cardAlertas);
        btnVolver = findViewById(R.id.btnVolver);
        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Agregar Alertas
        cardNuevoAviso.setOnClickListener(v -> {
            navegar(new Intent(this, agregarAlertas.class));
        });

        // Ir a Notificaciones
        cardAlertas.setOnClickListener(v -> {
            navegar(new Intent(this, Notificaciones.class));
        });

        // Volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private void navegar(Intent intent) {

        progressMenu.setVisibility(View.VISIBLE);

        cardNuevoAviso.setEnabled(false);
        cardAlertas.setEnabled(false);
        btnVolver.setEnabled(false);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressMenu.setVisibility(View.GONE);

        cardNuevoAviso.setEnabled(true);
        cardAlertas.setEnabled(true);
        btnVolver.setEnabled(true);
    }
}