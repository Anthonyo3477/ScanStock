package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class agregarAlertas extends AppCompatActivity {

    private EditText etNombre, etCategoria, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnGuardar, btnVolver, btnEscanear;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_alerta);

        // Firebase
        db = FirebaseFirestore.getInstance();

        // Inputs
        etNombre = findViewById(R.id.etNombre);
        etCategoria = findViewById(R.id.etCategoria);
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        // Botones
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);
        btnEscanear = findViewById(R.id.btnEscanear);

        // Guardar alerta
        btnGuardar.setOnClickListener(v -> guardarAlerta());

        // Escanear (más adelante)
        btnEscanear.setOnClickListener(v -> {
            Toast.makeText(this, "Función en desarrollo", Toast.LENGTH_SHORT).show();
        });

        // Volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private void guardarAlerta() {

        String nombre = etNombre.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String codigoBarras = etCodigoBarras.getText().toString().trim();

        int cantidad = Integer.parseInt(etCantidad.getText().toString());
        String stockBodega = etStockBodega.getText().toString().trim();
        String stockGondola = etStockGondola.getText().toString().trim();

        // Validaciones
        if (nombre.isEmpty() || categoria.isEmpty() ||  codigoBarras.isEmpty() || stockBodega.isEmpty() || stockGondola.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> alerta = new HashMap<>();

        alerta.put("nombre", nombre);
        alerta.put("categoria", categoria);
        alerta.put("codigoBarras", codigoBarras);
        alerta.put("cantidadFaltante", cantidad);
        alerta.put("stockBodega", Integer.parseInt(stockBodega));
        alerta.put("stockGondola", Integer.parseInt(stockGondola));
        alerta.put("estado", "pendiente");
        alerta.put("fecha", System.currentTimeMillis());

        db.collection("alertas").add(alerta).addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Alerta agregada correctamente", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
    private void limpiarCampos() {

        etNombre.setText("");
        etCategoria.setText("");
        etCodigoBarras.setText("");
        etCantidad.setText("");
        etStockBodega.setText("");
        etStockGondola.setText("");
    }
}