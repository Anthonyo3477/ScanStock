package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.notificacionAdapter;
import com.example.proyectoprueba.model.Notificacion;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Notificaciones extends AppCompatActivity {

    private RecyclerView recyclerViewNotificacion;
    private Button buttonVolver;

    // Adapter y lista
    private notificacionAdapter adapter;
    private List<Notificacion> listaNotificaciones;

    // Firebase
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificacion);

        // Componentes
        recyclerViewNotificacion = findViewById(R.id.recyclerViewNotificacion);
        buttonVolver = findViewById(R.id.btnVolver);

        // RecyclerView
        recyclerViewNotificacion.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Lista
        listaNotificaciones = new ArrayList<>();

        // Adapter
        adapter = new notificacionAdapter(listaNotificaciones);
        recyclerViewNotificacion.setAdapter(adapter);

        // Firebase
        db = FirebaseFirestore.getInstance();

        // Cargar notificaciones
        cargarNotificaciones();

        // Botón volver
        buttonVolver.setOnClickListener(v -> finish());
    }

    private void cargarNotificaciones() {

        db.collection("producto")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    listaNotificaciones.clear();

                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {

                        Producto producto = doc.toObject(Producto.class);

                        if (producto != null) {

                            // STOCK BODEGA
                            if (producto.getStockBodega() <= 5) {

                                listaNotificaciones.add(
                                        new Notificacion(
                                                "Stock bajo en Bodega: "
                                                        + producto.getNombre()
                                                        + " (" + producto.getStockBodega() + ")"
                                        )
                                );
                            }

                            // STOCK GONDOLA
                            if (producto.getStockGondola() <= 5) {

                                listaNotificaciones.add(
                                        new Notificacion(
                                                "Stock bajo en Góndola: "
                                                        + producto.getNombre()
                                                        + " (" + producto.getStockGondola() + ")"
                                        )
                                );
                            }
                        }
                    }

                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {

                    Toast.makeText(
                            this,
                            "Error al cargar las notificaciones",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }
}