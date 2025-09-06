package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarProducto extends AppCompatActivity {

    // Referencias a los campos
    private EditText etNombre, etCategoria, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnGuardar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_producto);

        // Vincular vistas con IDs
        etNombre = findViewById(R.id.etNombre);
        etCategoria = findViewById(R.id.etCategoria);
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);

        // Acción botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Tomar los valores escritos
                String nombre = etNombre.getText().toString();
                String categoria = etCategoria.getText().toString();
                String codigoBarras = etCodigoBarras.getText().toString();
                String cantidad = etCantidad.getText().toString();
                String stockBodega = etStockBodega.getText().toString();
                String stockGondola = etStockGondola.getText().toString();

                // Validar (simple ejemplo)
                if (nombre.isEmpty() || categoria.isEmpty() || codigoBarras.isEmpty()) {
                    Toast.makeText(AgregarProducto.this, "Completa todos los campos obligatorios", Toast.LENGTH_SHORT).show();
                } else {
                    // Aquí después conectamos con Firebase Firestore
                    Toast.makeText(AgregarProducto.this, "Producto guardado (simulación)", Toast.LENGTH_SHORT).show();
                    // Limpiar campos
                    etNombre.setText("");
                    etCategoria.setText("");
                    etCodigoBarras.setText("");
                    etCantidad.setText("");
                    etStockBodega.setText("");
                    etStockGondola.setText("");
                }
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
}