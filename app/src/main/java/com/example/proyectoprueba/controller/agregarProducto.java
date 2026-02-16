package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class agregarProducto extends AppCompatActivity {

    private EditText etNombre, etMarca, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Spinner spCategoria;
    private Button btnGuardar, btnVolver, btnEscanear;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_producto);

        etNombre = findViewById(R.id.etNombre);
        etMarca = findViewById(R.id.etMarca);
        spCategoria = findViewById(R.id.spCategoria);
        etFechaCaducidad = findViewById(R.id.etfechaCaducidad);
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);
        btnEscanear = findViewById(R.id.btnEscanear);

        db = FirebaseFirestore.getInstance();

        // configuracion de spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categorias_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> guardarProducto());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void guardarProducto() {

        String nombre = etNombre.getText().toString().trim();
        String marca = etMarca.getText().toString().trim();
        String categoria = spCategoria.getSelectedItem().toString();
        String fechaCaducidad = etFechaCaducidad.getText().toString().trim();

        String codigoBarrasStr = etCodigoBarras.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();
        String stockBodegaStr = etStockBodega.getText().toString().trim();
        String stockGondolaStr = etStockGondola.getText().toString().trim();

        Toast.makeText(this, "Categoria seleccionada: " + categoria, Toast.LENGTH_LONG).show();

        if (nombre.isEmpty() || marca.isEmpty() ||
                categoria.equals("Seleccione categoría") ||
                fechaCaducidad.isEmpty()) {

            Toast.makeText(this, "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!codigoBarrasStr.matches("\\d+") ||
                !cantidadStr.matches("\\d+") ||
                !stockBodegaStr.matches("\\d+") ||
                !stockGondolaStr.matches("\\d+")) {

            Toast.makeText(this, "Los campos numéricos deben contener solo números", Toast.LENGTH_SHORT).show();
            return;
        }

        int codigoBarras = Integer.parseInt(codigoBarrasStr);
        int cantidad = Integer.parseInt(cantidadStr);
        int stockBodega = Integer.parseInt(stockBodegaStr);
        int stockGondola = Integer.parseInt(stockGondolaStr);

        Map<String, Object> producto = new HashMap<>();
        producto.put("nombre", nombre);
        producto.put("marca", marca);
        producto.put("categoria", categoria);
        producto.put("fechaCaducidad", fechaCaducidad);
        producto.put("codigoBarras", codigoBarras);
        producto.put("cantidad", cantidad);
        producto.put("stockBodega", stockBodega);
        producto.put("stockGondola", stockGondola);

        db.collection("producto")
                .add(producto)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}