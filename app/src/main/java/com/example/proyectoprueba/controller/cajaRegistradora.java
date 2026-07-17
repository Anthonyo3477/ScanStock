package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.example.proyectoprueba.manager.alertasManager;

public class cajaRegistradora extends AppCompatActivity {

    private TextView txtCodigo, txtNombre, txtMarca, txtCategoria, txtStockBodega, txtStockGondola;
    private EditText etCantidadVenta;
    private Button btnEscanear, btnVender, btnVolver;
    private Producto productoActual;
    private DocumentSnapshot documentoActual;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private alertasManager manager;
    private ProgressBar progressLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caja);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtMarca = findViewById(R.id.txtMarca);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtStockBodega = findViewById(R.id.txtStockBodega);
        txtStockGondola = findViewById(R.id.txtStockGondola);

        etCantidadVenta = findViewById(R.id.etCantidadVenta);

        btnEscanear = findViewById(R.id.btnEscanear);
        btnVender = findViewById(R.id.btnVender);
        btnVolver = findViewById(R.id.btnVolver);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        manager = new alertasManager();

        btnEscanear.setOnClickListener(v -> iniciarEscaneo());
        btnVender.setOnClickListener(v -> venderProducto());
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

        long codigoBarras;
        try {
            codigoBarras = Long.parseLong(codigo);

        } catch (Exception e) {
            Toast.makeText(this, "Código inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("producto").whereEqualTo("codigoBarras", codigoBarras).get().addOnSuccessListener(query -> {
            if (query.isEmpty()) {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            documentoActual = query.getDocuments().get(0);
            productoActual = documentoActual.toObject(Producto.class);

            if (productoActual == null) {
                Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            productoActual.setId(documentoActual.getId());
            mostrarProducto();

        }).addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
    }

    private void mostrarProducto() {

        txtCodigo.setText("Código: " + productoActual.getCodigoBarras());
        txtNombre.setText("Producto: " + productoActual.getNombre());
        txtMarca.setText("Marca: " + productoActual.getMarca());
        txtCategoria.setText("Categoría: " + productoActual.getCategoria());
        txtStockBodega.setText("Stock Bodega: " + productoActual.getStockBodega());
        txtStockGondola.setText("Stock Góndola: " + productoActual.getStockGondola());
    }

    private void venderProducto() {
        if (productoActual == null) {
            Toast.makeText(this, "Primero escanee un producto", Toast.LENGTH_SHORT).show();
            return;
        }

        String cantidadTexto = etCantidadVenta.getText().toString().trim();

        if (cantidadTexto.isEmpty()) {
            etCantidadVenta.setError("Ingrese una cantidad");
            return;
        }

        int cantidadVenta = Integer.parseInt(cantidadTexto);

        if (cantidadVenta <= 0) {
            Toast.makeText(this, "Cantidad inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (productoActual.getStockGondola() < cantidadVenta) {
            Toast.makeText(this, "No hay suficiente stock en góndola", Toast.LENGTH_LONG).show();
            return;
        }

        int nuevoStock = productoActual.getStockGondola() - cantidadVenta;

        documentoActual.getReference().update("stockGondola", nuevoStock).addOnSuccessListener(unused -> {
            productoActual.setStockGondola(nuevoStock);
            manager.verificarProducto(productoActual);
            Toast.makeText(this, "Venta realizada correctamente", Toast.LENGTH_SHORT).show();

            limpiarPantalla();
        });
    }

    private void limpiarPantalla() {

        txtCodigo.setText("Código: --------");
        txtNombre.setText("Producto: --------");
        txtMarca.setText("Marca: --------");
        txtCategoria.setText("Categoría: --------");
        txtStockBodega.setText("Stock Bodega: --------");
        txtStockGondola.setText("Stock Góndola: --------");

        etCantidadVenta.setText("");

        productoActual = null;
        documentoActual = null;
    }
}