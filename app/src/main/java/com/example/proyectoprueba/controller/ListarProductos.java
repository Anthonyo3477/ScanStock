package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.productoAdapter;
import com.example.proyectoprueba.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ListarProductos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_productos);

        Button btnVolver = findViewById(R.id.btnVolver);
        RecyclerView recyclerProductos = findViewById(R.id.recyclerProductos);

        // Configurar el RecyclerView aquí

        recyclerProductos.setLayoutManager(new LinearLayoutManager(this));

        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(new Producto("Detergente", "", "Liempeiza", "", 100, 50,10 , 20));
        listaProductos.add(new Producto("Leche", "", "Lacteos", "", 100, 0, 5, 20));
        listaProductos.add(new Producto("Coca Cola", "", "Bebidas", "", 300, 100, 2, 20));


        productoAdapter adapter = new productoAdapter(listaProductos);
        recyclerProductos.setAdapter(adapter);

        // Boton para volver
        btnVolver.setOnClickListener(v -> finish());

    }
}