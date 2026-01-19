package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class categoriaProductos extends AppCompatActivity {

    private ImageButton btnPanaderia, btnAbarrotes, btnLacteos, btnRefrigerados, btnBebidas, btnLimpieza, btnBebe, btnMascota, btnOtros;
    private Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_productos);

        btnPanaderia = findViewById(R.id.btnPanaderia);
        btnAbarrotes = findViewById(R.id.btnAbarrotes);
        btnLacteos = findViewById(R.id.btnLacteos);

        btnRefrigerados = findViewById(R.id.btnRefrigerados);
        btnBebidas = findViewById(R.id.btnBebidas);
        btnLimpieza = findViewById(R.id.btnLimpieza);

        btnBebe = findViewById(R.id.btnBebe);
        btnMascota = findViewById(R.id.btnMascota);
        btnOtros = findViewById(R.id.btnOtros);

        Button btnVolver = findViewById(R.id.btnVolver);

        btnPanaderia.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });


        btnAbarrotes.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnLacteos.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnRefrigerados.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnBebidas.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnLimpieza.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnBebe.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnMascota.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnOtros.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}