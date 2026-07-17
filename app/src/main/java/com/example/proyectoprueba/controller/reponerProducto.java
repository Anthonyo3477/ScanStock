package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.manager.alertasManager;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;

public class reponerProducto extends AppCompatActivity {

    private EditText etCodigoBarras, etCantidadRepuesta;
    private RadioGroup radioGroupDestino;
    private RadioButton rbBodega, rbGondola;
    private Button btnVolver, btnConfirmar, btnEscanear;

    private FirebaseFirestore db;
    private alertasManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reponer_producto);
        db = FirebaseFirestore.getInstance();
        manager = new alertasManager();
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidadRepuesta = findViewById(R.id.etCantidadRepuesta);

        radioGroupDestino = findViewById(R.id.radioGroupDestino);
        rbBodega = findViewById(R.id.rbBodega);
        rbGondola = findViewById(R.id.rbGondola);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);
        btnEscanear = findViewById(R.id.btnEscanear);
        btnConfirmar.setOnClickListener(v -> validarDatos());
        btnVolver.setOnClickListener(v -> finish());
        btnEscanear.setOnClickListener(v -> iniciarEscaneo());
    }

    // ==========================================
    // ESCANEAR CÓDIGO DE BARRAS
    // ==========================================

    private void iniciarEscaneo() {
        ScanOptions opciones = new ScanOptions();

        opciones.setPrompt("Escanee el código del producto");
        opciones.setBeepEnabled(true);
        opciones.setOrientationLocked(false);

        barcodeLauncher.launch(opciones);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            String codigoEscaneado = result.getContents();
            etCodigoBarras.setText(codigoEscaneado);
        }
    });

    // ==========================================
    // VALIDAR DATOS
    // ==========================================

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
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadTexto);

        } catch (NumberFormatException e) {
            etCantidadRepuesta.setError("Ingrese un número válido");
            return;
        }

        if (cantidad <= 0) {
            Toast.makeText(this, "La cantidad debe ser mayor que cero", Toast.LENGTH_SHORT).show();
            return;
        }

        String destino;

        if (rbBodega.isChecked()) {
            destino = "Bodega";
        } else {
            destino = "Gondola";
        }
        reponerProducto(codigo, cantidad, destino);
    }

    // ==========================================
    // REPONER PRODUCTO
    // ==========================================

    private void reponerProducto(String codigo, int cantidad, String destino) {
        long codigoBarras;

        try {
            codigoBarras = Long.parseLong(codigo);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Código de barras inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(queryDocumentSnapshots -> {
            if (queryDocumentSnapshots.isEmpty()) {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(0);

            Producto producto = doc.toObject(Producto.class);
            if (producto == null) {Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            producto.setId(doc.getId());

            int stockBodegaActual = producto.getStockBodega();
            int stockGondolaActual = producto.getStockGondola();
            int nuevoStockBodega = stockBodegaActual;
            int nuevoStockGondola = stockGondolaActual;

            Map<String, Object> actualizacion = new HashMap<>();

            if (destino.equals("Bodega")) {
                nuevoStockBodega = stockBodegaActual + cantidad;
                actualizacion.put("stockBodega", nuevoStockBodega);

            } else {

                if (stockBodegaActual < cantidad) {
                    Toast.makeText(this, "No hay suficiente stock en bodega", Toast.LENGTH_LONG).show();
                    return;

                }

                nuevoStockBodega = stockBodegaActual - cantidad;
                nuevoStockGondola = stockGondolaActual + cantidad;
                actualizacion.put("stockBodega", nuevoStockBodega);
                actualizacion.put("stockGondola", nuevoStockGondola);
            }

            final int stockBodegaFinal = nuevoStockBodega;
            final int stockGondolaFinal = nuevoStockGondola;

            doc.getReference().update(actualizacion).addOnSuccessListener(unused -> {

                producto.setStockBodega(stockBodegaFinal);
                producto.setStockGondola(stockGondolaFinal);

                manager.verificarProducto(producto);
                Toast.makeText(this, "Producto repuesto correctamente", Toast.LENGTH_SHORT).show();

                finish();
            }).addOnFailureListener(e -> {Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });

        }).addOnFailureListener(e -> {Toast.makeText(this, "Error al buscar el producto", Toast.LENGTH_LONG).show();
        });
    }
}