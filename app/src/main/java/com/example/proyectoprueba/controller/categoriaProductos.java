package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

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

        btnVolver = findViewById(R.id.btnVolver);

        btnPanaderia.setOnClickListener(v -> abrirCategoria("Panaderia"));
        btnAbarrotes.setOnClickListener(v -> abrirCategoria("Abarrotes"));
        btnLacteos.setOnClickListener(v -> abrirCategoria("Lacteos"));
        btnRefrigerados.setOnClickListener(v -> abrirCategoria("Refrigerados"));
        btnBebidas.setOnClickListener(v -> abrirCategoria("Bebidas"));
        btnLimpieza.setOnClickListener(v -> abrirCategoria("Limpieza"));
        btnBebe.setOnClickListener(v -> abrirCategoria("Bebe"));
        btnMascota.setOnClickListener(v -> abrirCategoria("Mascota"));
        btnOtros.setOnClickListener(v -> abrirCategoria("Otros"));

        btnVolver.setOnClickListener(v -> finish());
    }

    private void abrirCategoria(String categoria){
        Intent intent = new Intent( categoriaProductos.this , ListarProductos.class);
        intent.putExtra("categoria", categoria);
        startActivity(intent );
    }
}