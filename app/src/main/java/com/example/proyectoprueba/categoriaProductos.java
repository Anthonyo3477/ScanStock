package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class categoriaProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_productos);

        ImageButton btnAseo = findViewById(R.id.btnAseo);
        ImageButton btnAlimentos = findViewById(R.id.btnAlimentos);
        ImageButton btnBebidas = findViewById(R.id.btnBebidas);
        ImageButton btnCongelados = findViewById(R.id.btnCongelados);
        ImageButton btnPanaderia = findViewById(R.id.btnPanaderia);
        ImageButton btnLimpieza = findViewById(R.id.btnLimpieza);

        Button btnVolver = findViewById(R.id.btnVolver);

        btnAseo.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });


        btnAlimentos.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnBebidas.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnCongelados.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnPanaderia.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnLimpieza.setOnClickListener(v -> {
            Intent intent = new Intent(categoriaProductos.this, ListarProductos.class);
            startActivity(intent);
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}