package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button btnAvisos = findViewById(R.id.btnAvisos);
        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnListarProductos = findViewById(R.id.btnListarProductos);
        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnAgregarAviso = findViewById(R.id.btnAgregarAviso);

        // Ir a Avisos
        btnAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Avisos.class);
                startActivity(intent);
            }
        });

        // Agregar Avisos
        btnAgregarAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, agregarAviso.class);
                startActivity(intent);
            }
        });

        // Ir a Productos
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, AgregarProducto.class);
                startActivity(intent);
            }
        });

        // Boton para Listar Productos
        btnListarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, categoriaProductos.class);
                startActivity(intent);
            }
        });

        // Botón volver al Home
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}