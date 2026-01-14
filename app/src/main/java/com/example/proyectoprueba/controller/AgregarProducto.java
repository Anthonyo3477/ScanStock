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

public class AgregarProducto extends AppCompatActivity {

    // Referencias a los campos
    private EditText etNombre, etMarca, etCategoria, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnGuardar, btnVolver, btnEscanear;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_producto);

        // inputs
        etNombre = findViewById(R.id.etNombre);
        etMarca = findViewById(R.id.etMarca);
        etCategoria = findViewById(R.id.etCategoria);
        etFechaCaducidad = findViewById(R.id.etfechaCaducidad);
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etFechaCaducidad = findViewById(R.id.etfechaCaducidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        // botones
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);
        btnEscanear = findViewById(R.id.btnEscanear);

        // Firebase
        db = FirebaseFirestore.getInstance();

        // Boton para Guardar
        btnGuardar.setOnClickListener(v -> guardarProducto());

        // Boton para escanear
        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aca deberia de ingresar al apartado donde se pueda ingresar el producto con el codigo de barras mediante a la camara
                // este apartado se realizara mas adelante, primero haremos el resto de ventanas y luego ingresararemos este apartado
            }
        });

        // Boton Volver al anterior ventana
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void guardarProducto() {

        String nombre = etNombre.getText().toString().trim();
        String marca = etMarca.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        String fechaCaducidad = etFechaCaducidad.getText().toString().trim();
        String codigoBarras = etCodigoBarras.getText().toString().trim();
        String cantidad = etCantidad.getText().toString().trim();
        String stockBodega = etStockBodega.getText().toString().trim();
        String stockGondola = etStockGondola.getText().toString().trim();

        if ( nombre.isEmpty() || marca.isEmpty() || categoria.isEmpty() || fechaCaducidad.isEmpty()
                || codigoBarras.isEmpty() || cantidad.isEmpty() || stockBodega.isEmpty() || stockGondola.isEmpty() ) {

            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;

        }

        // Crear un mapa con los datos del producto
        Map<String, Object> producto = new HashMap<>();
        producto.put("nombre", nombre);
        producto.put("marca", marca);
        producto.put("categoria", categoria);
        producto.put("fechaCaducidad", fechaCaducidad);
        producto.put("codigoBarras", codigoBarras);
        producto.put("cantidad", cantidad);
        producto.put("stockBodega", stockBodega);
        producto.put("stockGondola", stockGondola);

        // Guardar el producto en Firestore
        db.collection("producto").add(producto).addOnSuccessListener(documentReference -> {
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error al agregar el producto", Toast.LENGTH_SHORT).show();
        });
    }
}