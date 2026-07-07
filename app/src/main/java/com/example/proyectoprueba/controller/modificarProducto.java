package com.example.proyectoprueba.controller;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class modificarProducto extends AppCompatActivity {

    private EditText etNombreProducto, etMarca, etCategoria, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnActualizar, btnVolver;
    private FirebaseFirestore db;
    private String idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_producto);

        // Firebase
        db = FirebaseFirestore.getInstance();

        // Inputs
        etNombreProducto = findViewById(R.id.etNombreProducto);
        etMarca = findViewById(R.id.etMarca);
        etCategoria = findViewById(R.id.etCategoria);
        etFechaCaducidad = findViewById(R.id.etFechaCaducidad);

        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        // Botones
        btnActualizar = findViewById(R.id.btnActualizar);
        btnVolver = findViewById(R.id.btnVolver);

        // Recuperar datos
        idProducto = getIntent().getStringExtra("idProducto");

        etNombreProducto.setText(getIntent().getStringExtra("nombre"));
        etMarca.setText(getIntent().getStringExtra("marca"));
        etCategoria.setText(getIntent().getStringExtra("categoria"));
        etFechaCaducidad.setText(getIntent().getStringExtra("fechaCaducidad"));

        // Datos numericos
        long codigoBarras = getIntent().getIntExtra("codigoBarras", 0);
        int cantidad = getIntent().getIntExtra("cantidad", 0);
        int stockBodega = getIntent().getIntExtra("stockBodega", 0);
        int stockGondola = getIntent().getIntExtra("stockGondola", 0);

        etCodigoBarras.setText(String.valueOf(codigoBarras));
        etCantidad.setText(String.valueOf(cantidad));
        etStockBodega.setText(String.valueOf(stockBodega));
        etStockGondola.setText(String.valueOf(stockGondola));

        // Boton actualizar
        btnActualizar.setOnClickListener(v -> actualizarProducto());

        // Boton volver
        btnVolver.setOnClickListener(v -> finish());
    }

    private void actualizarProducto() {

        String nombre = etNombreProducto.getText().toString().trim();
        String marca = etMarca.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String fechaCaducidad = etFechaCaducidad.getText().toString().trim();

        long codigoBarras = Long.parseLong(etCodigoBarras.getText().toString().trim());
        int cantidad = Integer.parseInt(etCantidad.getText().toString().trim());
        int stockBodega = Integer.parseInt(etStockBodega.getText().toString().trim());
        int stockGondola = Integer.parseInt(etStockGondola.getText().toString().trim());

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
            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        })
            .addOnFailureListener(e -> {
                Toast.makeText(this,  "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });
    }
}