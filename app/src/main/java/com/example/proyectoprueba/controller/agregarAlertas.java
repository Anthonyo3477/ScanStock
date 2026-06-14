package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class agregarAlertas extends AppCompatActivity {

    private EditText etNombre;
    private EditText etCategoria;
    private EditText etCodigoBarras;
    private EditText etCantidad;
    private EditText etStockBodega;
    private EditText etStockGondola;
    private Button btnGuardar;
    private Button btnVolver;
    private Button btnEscanear;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_alerta);

        db = FirebaseFirestore.getInstance();

        // EditText
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

        btnGuardar.setOnClickListener(v -> guardarAlerta());

        btnEscanear.setOnClickListener(v ->
                Toast.makeText(this, "Función en desarrollo", Toast.LENGTH_SHORT).show()
        );

        btnVolver.setOnClickListener(v -> finish());
    }

    private void guardarAlerta() {

        String nombre = etNombre.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String codigoTexto = etCodigoBarras.getText().toString().trim();
        String cantidadTexto = etCantidad.getText().toString().trim();
        String stockBodegaTexto = etStockBodega.getText().toString().trim();
        String stockGondolaTexto = etStockGondola.getText().toString().trim();

        // Validaciones
        if (nombre.isEmpty() || categoria.isEmpty() || codigoTexto.isEmpty() || cantidadTexto.isEmpty() || stockBodegaTexto.isEmpty() || stockGondolaTexto.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int codigoBarras;
        int cantidad;
        int stockBodega;
        int stockGondola;

        try {
            codigoBarras = Integer.parseInt(codigoTexto);
            cantidad = Integer.parseInt(cantidadTexto);
            stockBodega = Integer.parseInt(stockBodegaTexto);
            stockGondola = Integer.parseInt(stockGondolaTexto);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Los campos numéricos son inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Buscar el producto para obtener el idProducto
        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Toast.makeText(this, "No existe un producto con ese código de barras", Toast.LENGTH_LONG).show();
                return;
            }

            DocumentSnapshot productoDoc = queryDocumentSnapshots.getDocuments().get(0);

            String idProducto = productoDoc.getId();

            Map<String, Object> alerta = new HashMap<>();

            alerta.put("idProducto", idProducto);
            alerta.put("nombre", nombre);
            alerta.put("categoria", categoria);
            alerta.put("codigoBarras", codigoBarras);
            alerta.put("cantidadFaltante", cantidad);
            alerta.put("stockBodega", stockBodega);
            alerta.put("stockGondola", stockGondola);
            alerta.put("estado", "pendiente");
            alerta.put("tipo", "Manual");
            alerta.put("fecha", System.currentTimeMillis());

            db.collection("alertas").add(alerta).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Alerta agregada correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();

            }).addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());

        }).addOnFailureListener(e -> Toast.makeText(this, "Error al buscar el producto", Toast.LENGTH_LONG).show());
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