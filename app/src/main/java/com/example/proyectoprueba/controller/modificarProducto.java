package com.example.proyectoprueba.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.manager.ProgressManager;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class modificarProducto extends AppCompatActivity {

    private EditText etNombreProducto, etMarca, etCategoria, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnActualizar, btnVolver;
    private ProgressManager progressManager;
    private FirebaseFirestore db;
    private String idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_producto);

        db = FirebaseFirestore.getInstance();

        etNombreProducto = findViewById(R.id.etNombreProducto);
        etMarca = findViewById(R.id.etMarca);
        etCategoria = findViewById(R.id.etCategoria);
        etFechaCaducidad = findViewById(R.id.etFechaCaducidad);

        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        btnActualizar = findViewById(R.id.btnActualizar);
        btnVolver = findViewById(R.id.btnVolver);

        ProgressBar progressMenu = findViewById(R.id.progress_menu);
        progressManager = new ProgressManager(progressMenu, btnActualizar, btnVolver, etNombreProducto, etMarca, etCategoria, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola);


        idProducto = getIntent().getStringExtra("idProducto");
        etNombreProducto.setText(getIntent().getStringExtra("nombre"));
        etMarca.setText(getIntent().getStringExtra("marca"));
        etCategoria.setText(getIntent().getStringExtra("categoria"));
        etFechaCaducidad.setText(getIntent().getStringExtra("fechaCaducidad"));

        long codigoBarras = getIntent().getLongExtra("codigoBarras", 0);
        int cantidad = getIntent().getIntExtra("cantidad", 0);
        int stockBodega = getIntent().getIntExtra("stockBodega", 0);
        int stockGondola = getIntent().getIntExtra("stockGondola", 0);

        etCodigoBarras.setText(String.valueOf(codigoBarras));
        etCantidad.setText(String.valueOf(cantidad));
        etStockBodega.setText(String.valueOf(stockBodega));
        etStockGondola.setText(String.valueOf(stockGondola));

        btnActualizar.setOnClickListener(v -> actualizarProducto());

        btnVolver.setOnClickListener(v -> finish());
    }

    private void actualizarProducto() {

        progressManager.mostrar();

        String nombre = etNombreProducto.getText().toString().trim();
        String marca = etMarca.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String fechaCaducidad = etFechaCaducidad.getText().toString().trim();

        if (nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty() || fechaCaducidad.isEmpty() || etCodigoBarras.getText().toString().trim().isEmpty() ||
                etCantidad.getText().toString().trim().isEmpty() || etStockBodega.getText().toString().trim().isEmpty() || etStockGondola.getText().toString().trim().isEmpty()) {
            progressManager.ocultar();
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        long codigoBarras;
        int cantidad;
        int stockBodega;
        int stockGondola;

        try {

            codigoBarras = Long.parseLong(etCodigoBarras.getText().toString().trim());
            cantidad = Integer.parseInt(etCantidad.getText().toString().trim());
            stockBodega = Integer.parseInt(etStockBodega.getText().toString().trim());
            stockGondola = Integer.parseInt(etStockGondola.getText().toString().trim());

        } catch (NumberFormatException e) {

            progressManager.ocultar();
            Toast.makeText(this, "Los campos numéricos son inválidos", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> productoActualizado = new HashMap<>();

        productoActualizado.put("nombre", nombre);
        productoActualizado.put("marca", marca);
        productoActualizado.put("categoria", categoria);
        productoActualizado.put("fechaCaducidad", fechaCaducidad);

        productoActualizado.put("codigoBarras", codigoBarras);
        productoActualizado.put("cantidad", cantidad);
        productoActualizado.put("stockBodega", stockBodega);
        productoActualizado.put("stockGondola", stockGondola);

        db.collection("producto").document(idProducto).update(productoActualizado).addOnSuccessListener(unused -> {
            progressManager.ocultar();
            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();

        }).addOnFailureListener(e -> {
            progressManager.ocultar();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }
}