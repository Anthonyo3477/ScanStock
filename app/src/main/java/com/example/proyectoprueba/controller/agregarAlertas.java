package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.manager.ProgressManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;

public class agregarAlertas extends AppCompatActivity {

    private EditText etNombre, etCategoria, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnGuardar, btnVolver, btnEscanear;
    private ProgressBar progressMenu;
    private ProgressManager progressManager;
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

        // ProgressBar
        progressMenu = findViewById(R.id.progress_menu);
        progressManager = new ProgressManager(progressMenu, btnGuardar, btnVolver, btnEscanear, etNombre, etCategoria, etCodigoBarras, etCantidad, etStockBodega, etStockGondola);

        btnGuardar.setOnClickListener(v -> guardarAlerta());
        btnEscanear.setOnClickListener(v -> iniciarEscaneo());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void iniciarEscaneo() {

        ScanOptions opciones = new ScanOptions();
        opciones.setPrompt("Escanee el código del producto");
        opciones.setBeepEnabled(true);
        opciones.setOrientationLocked(false);

        barcodeLauncher.launch(opciones);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            buscarProducto(result.getContents());
        }
    });

    private void buscarProducto(String codigo) {

        progressManager.mostrar();
        long codigoBarras;

        try {
            codigoBarras = Long.parseLong(codigo);

        } catch (Exception e) {
            progressManager.ocultar();
            Toast.makeText(this, "Código inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(query -> {
            if (query.isEmpty()) {
                progressManager.ocultar();
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            DocumentSnapshot doc = query.getDocuments().get(0);
            etNombre.setText(doc.getString("nombre"));
            etCategoria.setText(doc.getString("categoria"));
            etCodigoBarras.setText(String.valueOf(codigoBarras));

            Long stockBodega = doc.getLong("stockBodega");
            Long stockGondola = doc.getLong("stockGondola");

            etStockBodega.setText(String.valueOf(stockBodega != null ? stockBodega : 0));
            etStockGondola.setText(String.valueOf(stockGondola != null ? stockGondola : 0));

            progressManager.ocultar();

        }).addOnFailureListener(e -> {
            progressManager.ocultar();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private void guardarAlerta() {

        progressManager.mostrar();

        String nombre = etNombre.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String codigoTexto = etCodigoBarras.getText().toString().trim();
        String cantidadTexto = etCantidad.getText().toString().trim();
        String stockBodegaTexto = etStockBodega.getText().toString().trim();
        String stockGondolaTexto = etStockGondola.getText().toString().trim();

        if (nombre.isEmpty() || categoria.isEmpty() || codigoTexto.isEmpty() || cantidadTexto.isEmpty() || stockBodegaTexto.isEmpty() || stockGondolaTexto.isEmpty()) {
            progressManager.ocultar();
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        long codigoBarras;
        int cantidad;
        int stockBodega;
        int stockGondola;

        try {

            codigoBarras = Long.parseLong(codigoTexto);
            cantidad = Integer.parseInt(cantidadTexto);
            stockBodega = Integer.parseInt(stockBodegaTexto);
            stockGondola = Integer.parseInt(stockGondolaTexto);

        } catch (NumberFormatException e) {
            progressManager.ocultar();
            Toast.makeText(this, "Los campos numéricos son inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                progressManager.ocultar();
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
                progressManager.ocultar();
                Toast.makeText(this, "Alerta agregada correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();

            }).addOnFailureListener(e -> {
                progressManager.ocultar();
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });

        }).addOnFailureListener(e -> {
            progressManager.ocultar();
            Toast.makeText(this, "Error al buscar el producto", Toast.LENGTH_LONG).show();
        });
    }

    private void limpiarCampos() {

        etNombre.setText("");
        etCategoria.setText("");
        etCodigoBarras.setText("");
        etCantidad.setText("");
        etStockBodega.setText("");
        etStockGondola.setText("");

        etNombre.requestFocus();
    }
}