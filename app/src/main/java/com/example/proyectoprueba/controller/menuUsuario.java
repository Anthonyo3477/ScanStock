package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class menuUsuario extends AppCompatActivity {

    private Button btnAvisoProducto, btnAviso, btnVolver;
    private ProgressBar progressMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario);

        btnAvisoProducto = findViewById(R.id.btnAvisoProducto);
        btnAviso = findViewById(R.id.btnAviso);
        btnVolver = findViewById(R.id.btnVolver);

        progressMenu = findViewById(R.id.progress_menu);

        // Ir a Productos
        btnAvisoProducto.setOnClickListener( v ->{
            navegar(new Intent(this, agregarAviso.class));
        });


        btnAviso.setOnClickListener( v ->{
            navegar(new Intent(this, Avisos.class));
        });

        // Botón volver al Home
        btnVolver.setOnClickListener( v ->{
            finish();
        });
    }

    private void navegar (Intent intent){
        progressMenu.setVisibility(View.VISIBLE);

        btnAvisoProducto.setEnabled(false);
        btnAviso.setEnabled(false);
        btnVolver.setEnabled(false);

        startActivity(intent);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progressMenu.setVisibility(View.GONE);

        btnAvisoProducto.setEnabled(true);
        btnAviso.setEnabled(true);
        btnVolver.setEnabled(true);
    }
}
