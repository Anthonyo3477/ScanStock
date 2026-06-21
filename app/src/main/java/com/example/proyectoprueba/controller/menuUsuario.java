package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class menuUsuario extends AppCompatActivity {

    private Button btnAvisoProducto, btnAlertas, btnVolver;
    private ProgressBar progressMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario);

        btnAvisoProducto = findViewById(R.id.btnAvisoProducto);
        btnAlertas = findViewById(R.id.btnAlertas);
        btnVolver = findViewById(R.id.btnVolver);

        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Productos
        btnAvisoProducto.setOnClickListener( v ->{
            navegar(new Intent(this, agregarAlertas.class));
        });


        btnAlertas.setOnClickListener( v ->{
            navegar(new Intent(this, Notificaciones.class));
        });

        // Botón volver al Home
        btnVolver.setOnClickListener( v ->{
            finish();
        });
    }

    private void navegar (Intent intent){
        progressMenu.setVisibility(View.VISIBLE);

        btnAvisoProducto.setEnabled(false);
        btnAlertas.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        btnAvisoProducto.setEnabled(true);
        btnAlertas.setEnabled(true);
        btnVolver.setEnabled(true);
    }
}
