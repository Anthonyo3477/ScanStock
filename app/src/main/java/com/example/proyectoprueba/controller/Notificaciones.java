package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.notificacionAdapter;
import com.example.proyectoprueba.manager.alertasManager;
import com.example.proyectoprueba.model.Notificacion;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Notificaciones extends AppCompatActivity {

    private RecyclerView recyclerViewNotificacion;
    private Button buttonVolver;

    private notificacionAdapter adapter;
    private List<Notificacion> listaNotificaciones;

    private FirebaseFirestore db;
    private alertasManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_alertas);

        recyclerViewNotificacion = findViewById(R.id.recyclerViewNotificacion);
        buttonVolver = findViewById(R.id.btnVolver);

        recyclerViewNotificacion.setLayoutManager(new LinearLayoutManager(this));

        listaNotificaciones = new ArrayList<>();

        adapter = new notificacionAdapter(listaNotificaciones, notificacion -> {
            Intent intent = new Intent(Notificaciones.this, reponerProducto.class);

            intent.putExtra("idProducto", notificacion.getIdProducto());
            intent.putExtra("tipo", notificacion.getTipo());

            startActivity(intent);

        });

        recyclerViewNotificacion.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        manager = new alertasManager();
        cargarNotificaciones();
        buttonVolver.setOnClickListener(v -> finish());

    }

    private void cargarNotificaciones() {

        db.collection("producto").get().addOnSuccessListener(query -> {
            for (DocumentSnapshot doc : query.getDocuments()) {
                Producto producto = doc.toObject(Producto.class);
                if (producto == null)
                    continue;
                producto.setId(doc.getId());
                manager.verificarProducto(producto);

            }
            cargarAlertasAutomaticas();

        }).addOnFailureListener(e -> Toast.makeText(this, "Error al cargar productos", Toast.LENGTH_SHORT).show());
    }

    private void cargarAlertasAutomaticas() {

        listaNotificaciones.clear();

        db.collection("alertas").whereEqualTo("estado", "pendiente").whereEqualTo("origen", "Automatica").get().addOnSuccessListener(query -> {
            for (DocumentSnapshot doc : query.getDocuments()) {
                listaNotificaciones.add(
                        new Notificacion(
                                doc.getString("idProducto"),
                                doc.getString("mensaje"),
                                doc.getString("prioridad"),
                                doc.getString("tipo"),
                                doc.getString("estado")
                        )
                );
            }
            cargarAlertasManuales();
        });

    }

    // Alertas creadas por el trabajador
    private void cargarAlertasManuales() {

        db.collection("alertas").whereEqualTo("estado", "pendiente").whereEqualTo("origen", "Manual").get().addOnSuccessListener(query -> {
            for (DocumentSnapshot doc : query.getDocuments()) {
                String nombre = doc.getString("nombre");
                Long cantidad = doc.getLong("cantidadFaltante");

                if (cantidad == null)
                    cantidad = 0L;
                String mensaje = "ALERTA MANUAL - "
                        + nombre
                        + " (Faltan "
                        + cantidad
                        + " unidades)";

                listaNotificaciones.add(new Notificacion(doc.getString("idProducto"), mensaje, "Manual", "Manual", "pendiente"));
            }
            adapter.notifyDataSetChanged();
        });
    }
}