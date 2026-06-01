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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notificaciones extends AppCompatActivity {

    private RecyclerView recyclerViewNotificacion;
    private Button buttonVolver;
    private notificacionAdapter adapter;
    private List<Notificacion> listaNotificaciones;
    private FirebaseFirestore db;
    private static final int STOCK_CRITICO = 2;
    private static final int STOCK_BAJO = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_alertas);

        recyclerViewNotificacion = findViewById(R.id.recyclerViewNotificacion);
        buttonVolver = findViewById(R.id.btnVolver);

        recyclerViewNotificacion.setLayoutManager(new LinearLayoutManager(this));
        listaNotificaciones = new ArrayList<>();

        adapter = new notificacionAdapter(listaNotificaciones);
        recyclerViewNotificacion.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        cargarNotificaciones();
        buttonVolver.setOnClickListener(v -> finish());
    }

    private void cargarNotificaciones() {
        db.collection("producto").get().addOnSuccessListener(queryDocumentSnapshots -> {
                    listaNotificaciones.clear();

                    for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                        Producto producto = doc.toObject(Producto.class);
                        if (producto == null) {
                            continue;
                        }
                        producto.setId(doc.getId());

                        // STOCK BODEGA
                        if (producto.getStockBodega() <= STOCK_CRITICO) {
                            String mensaje =
                                    "Stock Crítico - Bodega: "
                                            + producto.getNombre()
                                            + " ("
                                            + producto.getStockBodega()
                                            + ")";

                            listaNotificaciones.add(new Notificacion(mensaje)
                            );

                            guardarAlertaSiNoExiste(
                                    producto,
                                    "Bodega",
                                    mensaje,
                                    "Critica"
                            );

                        } else if (producto.getStockBodega() <= STOCK_BAJO) {
                            String mensaje =
                                    "Stock Bajo - Bodega: "
                                            + producto.getNombre()
                                            + " ("
                                            + producto.getStockBodega()
                                            + ")";

                            listaNotificaciones.add(new Notificacion(mensaje)
                            );

                            guardarAlertaSiNoExiste(
                                    producto,
                                    "Bodega",
                                    mensaje,
                                    "Baja"
                            );
                        }

                        // STOCK GONDOLA
                        if (producto.getStockGondola() <= STOCK_CRITICO) {
                            String mensaje =
                                    "Stock Crítico - Góndola: "
                                            + producto.getNombre()
                                            + " ("
                                            + producto.getStockGondola()
                                            + ")";

                            listaNotificaciones.add(new Notificacion(mensaje)
                            );

                            guardarAlertaSiNoExiste(
                                    producto,
                                    "Gondola",
                                    mensaje,
                                    "Critica"
                            );

                        } else if (producto.getStockGondola() <= STOCK_BAJO) {
                            String mensaje =
                                    "Stock Bajo - Góndola: "
                                            + producto.getNombre()
                                            + " ("
                                            + producto.getStockGondola()
                                            + ")";

                            listaNotificaciones.add(new Notificacion(mensaje)
                            );

                            guardarAlertaSiNoExiste(
                                    producto,
                                    "Gondola",
                                    mensaje,
                                    "Baja"
                            );
                        }
                    }

                    adapter.notifyDataSetChanged();
                    cargarAlertasManuales();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar las notificaciones", Toast.LENGTH_SHORT).show();

                });
    }

    // Metodo para las Alertas Manuales
    private void cargarAlertasManuales() {

        db.collection("alertas").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {

                String nombre = doc.getString("nombre");
                long cantidad = doc.getLong("cantidadFaltante");
                String mensaje =
                        "ALERTA MANUAL - "
                                + nombre
                                + " (Faltan "
                                + cantidad
                                + " unidades)";
                listaNotificaciones.add(new Notificacion(mensaje)
                );
            }
            adapter.notifyDataSetChanged();
        });

    }

    // Metodo para guardar alertas en FireStore
    private void guardarAlerta(
            Producto producto,
            String tipo,
            String mensaje,
            String prioridad) {

        Map<String, Object> alerta = new HashMap<>();

        alerta.put("idProducto", producto.getId());
        alerta.put("tipo", tipo);
        alerta.put("mensaje", mensaje);
        alerta.put("prioridad", prioridad);
        alerta.put("fecha", System.currentTimeMillis());
        alerta.put("estado", "pendiente");

        db.collection("alertas")
                .add(alerta);
    }

    // Metodo para Alertas Duplicadas
    private void guardarAlertaSiNoExiste(
            Producto producto,
            String tipo,
            String mensaje,
            String prioridad) {

        db.collection("alertas")
                .whereEqualTo("idProducto", producto.getId())
                .whereEqualTo("tipo", tipo)
                .whereEqualTo("estado", "pendiente")
                .get()
                .addOnSuccessListener(query -> {

                    if (query.isEmpty()) {

                        guardarAlerta(
                                producto,
                                tipo,
                                mensaje,
                                prioridad
                        );
                    }
                });
    }
}