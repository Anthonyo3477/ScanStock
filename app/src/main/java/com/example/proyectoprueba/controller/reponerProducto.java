package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class reponerProducto extends AppCompatActivity {

    private EditText etCodigoBarras;
    private EditText etCantidadRepuesta;

    private RadioGroup radioGroupDestino;
    private RadioButton rbBodega;
    private RadioButton rbGondola;

    private Button btnConfirmar;
    private Button btnVolver;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reponer_producto);

        db = FirebaseFirestore.getInstance();

        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidadRepuesta = findViewById(R.id.etCantidadRepuesta);

        radioGroupDestino = findViewById(R.id.radioGroupDestino);

        rbBodega = findViewById(R.id.rbBodega);
        rbGondola = findViewById(R.id.rbGondola);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);

        btnConfirmar.setOnClickListener(v -> validarDatos());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void validarDatos() {

        String codigo = etCodigoBarras.getText().toString().trim();
        String cantidadTexto = etCantidadRepuesta.getText().toString().trim();

        if (codigo.isEmpty()) {
            etCodigoBarras.setError("Ingrese un código");
            return;
        }

        if (cantidadTexto.isEmpty()) {
            etCantidadRepuesta.setError("Ingrese una cantidad");
            return;
        }

        if (!rbBodega.isChecked() && !rbGondola.isChecked()) {
            Toast.makeText(this, "Seleccione Bodega o Góndola", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidad = Integer.parseInt(cantidadTexto);
        String destino = rbBodega.isChecked() ? "Bodega" : "Gondola";

        reponerProducto(codigo, cantidad, destino);
    }

    private void reponerProducto(String codigo, int cantidad, String destino) {
        long codigoBarras;

        try {
            codigoBarras = Integer.parseInt(codigo);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Código de barras inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cantidad <= 0) {
            Toast.makeText(this, "La cantidad debe ser mayor que cero", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);
            Producto producto = doc.toObject(Producto.class);

            if (producto == null) {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            producto.setId(doc.getId());
            Map<String, Object> actualizacion = new HashMap<>();

            if (destino.equals("Bodega")) {
                actualizacion.put("stockBodega", producto.getStockBodega() + cantidad);

            } else {
                if (producto.getStockBodega() < cantidad) {
                    Toast.makeText(this, "No hay suficiente stock en bodega", Toast.LENGTH_LONG).show();
                    return;
                }

                actualizacion.put("stockBodega", producto.getStockBodega() - cantidad);
                actualizacion.put("stockGondola", producto.getStockGondola() + cantidad);

            }

            doc.getReference().update(actualizacion).addOnSuccessListener(aVoid -> {

                resolverAlerta(producto.getCodigoBarras());
                Toast.makeText(this, "Producto reponido correctamente", Toast.LENGTH_SHORT).show();

                finish();

            }).addOnFailureListener(e -> Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());

        }).addOnFailureListener(e -> Toast.makeText(this, "Error al buscar el producto", Toast.LENGTH_LONG).show());
    }

    private void resolverAlerta(long codigoBarras) {
        db.collection("alertas").whereEqualTo("codigoBarras", codigoBarras).whereEqualTo("estado", "pendiente").get().addOnSuccessListener(queryDocumentSnapshots -> {
            Toast.makeText(this, "Alertas encontradas: " + queryDocumentSnapshots.size(), Toast.LENGTH_LONG).show();

            for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                doc.getReference().update("estado", "resuelta");
            }
        });
    }
}