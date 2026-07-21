package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.movimientoAdapter;
import com.example.proyectoprueba.model.Movimiento;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Movimientos extends AppCompatActivity {

    private RecyclerView recyclerMovimientos;
    private Button btnVolver;

    private FirebaseFirestore db;

    private List<Movimiento> listaMovimientos;
    private movimientoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.historial_movimientos);

        db = FirebaseFirestore.getInstance();

        recyclerMovimientos = findViewById(R.id.recyclerMovimientos);
        btnVolver = findViewById(R.id.btnVolver);

        listaMovimientos = new ArrayList<>();

        recyclerMovimientos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new movimientoAdapter(this, listaMovimientos);

        recyclerMovimientos.setAdapter(adapter);

        btnVolver.setOnClickListener(v -> finish());
        cargarMovimientos();
    }

    private void cargarMovimientos() {

        db.collection("movimientos").orderBy("fecha", Query.Direction.DESCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {

            listaMovimientos.clear();
            for (var document : queryDocumentSnapshots) {
                Movimiento movimiento = document.toObject(Movimiento.class);
                movimiento.setId(document.getId());
                listaMovimientos.add(movimiento);
            }

            adapter.notifyDataSetChanged();

        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error al cargar los movimientos", Toast.LENGTH_LONG).show();
        });
    }
}