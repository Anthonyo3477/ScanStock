package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.adapter.historialAdapter;
import com.example.proyectoprueba.model.historialAlertas;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class HistorialAlertas extends AppCompatActivity {

    private RecyclerView recyclerViewHistorial;
    private Button btnVolver;
    private historialAdapter adapter;
    private List<historialAlertas> listaHistorial;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial_alertas);

        recyclerViewHistorial = findViewById(R.id.recyclerHistorial);
        btnVolver = findViewById(R.id.btnVolver);

        db = FirebaseFirestore.getInstance();

        listaHistorial = new ArrayList<>();
        adapter = new historialAdapter(listaHistorial);

        recyclerViewHistorial.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewHistorial.setAdapter(adapter);
        cargarHistorial();
        btnVolver.setOnClickListener(v -> finish());
    }

    private void cargarHistorial() {

        db.collection("alertas").whereEqualTo("estado", "resuelta").get().addOnSuccessListener(queryDocumentSnapshots -> {
            listaHistorial.clear();
            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                historialAlertas alerta = new historialAlertas();

                alerta.setNombre(doc.getString("nombre"));
                alerta.setCategoria(doc.getString("categoria"));

                Long codigo = doc.getLong("codigoBarras");
                alerta.setCodigoBarras(codigo != null ? codigo.intValue() : 0);

                Long cantidad = doc.getLong("cantidadFaltante");
                alerta.setCantidadFaltante(cantidad != null ? cantidad.intValue() : 0);

                Long stockBodega = doc.getLong("stockBodega");
                alerta.setStockBodega(stockBodega != null ? stockBodega.intValue() : 0);

                Long stockGondola = doc.getLong("stockGondola");
                alerta.setStockGondola(stockGondola != null ? stockGondola.intValue() : 0);
                alerta.setEstado(doc.getString("estado"));

                listaHistorial.add(alerta);

            }
            adapter.notifyDataSetChanged();
        }).addOnFailureListener(e -> Toast.makeText(this, "Error al cargar historial", Toast.LENGTH_SHORT).show());
    }
}