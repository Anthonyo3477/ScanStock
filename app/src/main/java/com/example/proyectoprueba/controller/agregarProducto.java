package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.manager.ProgressManager;
import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.HashMap;
import java.util.Map;

public class agregarProducto extends AppCompatActivity {

    private EditText etNombre, etMarca, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Spinner spCategoria;
    private Button btnGuardar, btnVolver, btnEscanear;
    private ProgressBar progressMenu;
    private ProgressManager progressManager;

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

        progressMenu = findViewById(R.id.progress_menu);
        progressManager = new ProgressManager(progressMenu, btnGuardar, btnVolver, btnEscanear, etNombre, etMarca, spCategoria, etFechaCaducidad, etCodigoBarras, etCantidad, etStockBodega, etStockGondola);

        db = FirebaseFirestore.getInstance();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categorias_array, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_drop_item);
        spCategoria.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> guardarProducto());
        btnEscanear.setOnClickListener(v -> iniciarEscaneo());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void guardarProducto() {

        progressManager.mostrar();

        String nombre = etNombre.getText().toString().trim();
        String marca = etMarca.getText().toString().trim();
        String categoria = spCategoria.getSelectedItem().toString();
        String fechaCaducidad = etFechaCaducidad.getText().toString().trim();

        String codigoBarrasStr = etCodigoBarras.getText().toString().trim();
        String cantidadStr = etCantidad.getText().toString().trim();
        String stockBodegaStr = etStockBodega.getText().toString().trim();
        String stockGondolaStr = etStockGondola.getText().toString().trim();

        if (nombre.isEmpty() || marca.isEmpty() || categoria.equals("Seleccione categoría") || fechaCaducidad.isEmpty()) {
            progressManager.ocultar();
            Toast.makeText(this, "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!codigoBarrasStr.matches("\\d+") || !cantidadStr.matches("\\d+") || !stockBodegaStr.matches("\\d+") || !stockGondolaStr.matches("\\d+")) {
            progressManager.ocultar();
            Toast.makeText(this, "Los campos numéricos deben contener solo números", Toast.LENGTH_SHORT).show();
            return;
        }

        long codigoBarras = Long.parseLong(codigoBarrasStr);
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

        db.collection("producto").add(producto).addOnSuccessListener(documentReference -> {
            progressManager.ocultar();
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }).addOnFailureListener(e -> {
            progressManager.ocultar();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLaunch =
            registerForActivityResult(new ScanContract(), result -> {
                progressManager.ocultar();
                if (result.getContents() != null) {
                    etCodigoBarras.setText(result.getContents());
                }
            });

    private void iniciarEscaneo() {

        progressManager.mostrar();

        ScanOptions opciones = new ScanOptions();

        opciones.setPrompt("Escanee el código de barras");
        opciones.setBeepEnabled(true);
        opciones.setOrientationLocked(false);

        barcodeLaunch.launch(opciones);
    }
}