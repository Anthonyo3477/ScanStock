package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoprueba.R;

public class menuSuperAdmin extends AppCompatActivity {

    private CardView cardAlertas, cardHistorial, cardAvisos, cardPersonal, cardUsuarios, cardReportes;
    private Button btnVolver;
    private ProgressBar progressMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_super_admin);

        cardAlertas = findViewById(R.id.cardAlertas);
        cardHistorial = findViewById(R.id.cardHistorial);
        cardAvisos = findViewById(R.id.cardAvisos);
        cardPersonal = findViewById(R.id.cardPersonal);
        cardUsuarios = findViewById(R.id.cardUsuarios);
        cardReportes = findViewById(R.id.cardReportes);

        btnVolver = findViewById(R.id.btnVolver);
        progressMenu = findViewById(R.id.progress_menu);

        // Alertas
        cardAlertas.setOnClickListener(v -> navegar(new Intent(this, Notificaciones.class))
        );

        // Historial
        cardHistorial.setOnClickListener(v -> navegar(new Intent(this, HistorialAlertas.class))
        );

        // Agregar Aviso
        cardAvisos.setOnClickListener(v -> navegar(new Intent(this, agregarAlertas.class))
        );

        // Agregar Personal
        cardPersonal.setOnClickListener(v -> navegar(new Intent(this, registrarPersonal.class))
        );

        // Listar Usuarios
        cardUsuarios.setOnClickListener(v -> navegar(new Intent(this, listarUsuarios.class))
        );

        // Reportes
        cardReportes.setOnClickListener(v -> {

            // Cuando tengas creada la Activity:
            // navegar(new Intent(this, GenerarReporte.class));

        });
        btnVolver.setOnClickListener(v -> finish());
    }

    private void navegar(Intent intent) {

        progressMenu.setVisibility(View.VISIBLE);

        cardAlertas.setEnabled(false);
        cardHistorial.setEnabled(false);
        cardAvisos.setEnabled(false);
        cardPersonal.setEnabled(false);
        cardUsuarios.setEnabled(false);
        cardReportes.setEnabled(false);

        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        progressMenu.setVisibility(View.GONE);

        cardAlertas.setEnabled(true);
        cardHistorial.setEnabled(true);
        cardAvisos.setEnabled(true);
        cardPersonal.setEnabled(true);
        cardUsuarios.setEnabled(true);
        cardReportes.setEnabled(true);

        btnVolver.setEnabled(true);
    }
}
