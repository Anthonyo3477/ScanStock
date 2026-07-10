package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.example.proyectoprueba.model.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.proyectoprueba.model.Producto;

import androidx.activity.result.ActivityResultLauncher;

import com.google.firebase.firestore.DocumentSnapshot;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class cajaRegistradora extends AppCompatActivity {

    private TextView txtCodigo, txtNombre, txtMarca, txtCategoria, txtStockBodega, txtStockGondola;
    private Button btnEscanear, btnVender, btnVolver;
    private Producto productoActual;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private ProgressBar progressLogin;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caja);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtMarca = findViewById(R.id.txtMarca);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtStockBodega = findViewById(R.id.txtStockBodega);
        txtStockGondola = findViewById(R.id.txtStockGondola);

        btnEscanear = findViewById(R.id.btnEscanear);
        btnVender = findViewById(R.id.btnVender);
        btnVolver = findViewById(R.id.btnVolver);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnEscanear.setOnClickListener( v -> iniciarEscaneo());
        btnVolver.setOnClickListener( v -> finish());
    }


    private void iniciarEscaneo(){
        ScanOptions opciones = new ScanOptions();

        opciones.setPrompt("Escanee el codigo del prodcuto");
        opciones.setBeepEnabled(true);
        opciones.setOrientationLocked(false);

        barcodeLauncher.launch(opciones);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() != null){
            buscarProducto(result.getContents());
        }
    });

    private void buscarProducto (String codigo) {
        long codigoBarras;

        try{
            codigoBarras = Long.parseLong(codigo);
        }catch (Exception e ){
            Toast.makeText(this,"Código inválido",Toast.LENGTH_SHORT).show();
            return;
        }db.collection("producto").whereEqualTo("codigoBarras",codigoBarras).get().addOnSuccessListener(query ->{
            if(query.isEmpty()){
                Toast.makeText(this,"Producto no encontrado",Toast.LENGTH_SHORT).show();
                return;
            }
            DocumentSnapshot doc = query.getDocuments().get(0);
            productoActual = doc.toObject(Producto.class);

            if(productoActual == null){
                Toast.makeText(this,"Producto no encontrado",Toast.LENGTH_SHORT).show();
                return;
            }
            productoActual.setId(doc.getId());
            mostrarProducto();
        });
    }

    private void mostrarProducto(){

        txtCodigo.setText("Código: " + productoActual.getCodigoBarras());
        txtNombre.setText("Producto: " + productoActual.getNombre());
        txtMarca.setText("Marca: " + productoActual.getMarca());
        txtCategoria.setText("Categoría: " + productoActual.getCategoria());
        txtStockBodega.setText("Stock Bodega: " + productoActual.getStockBodega());
        txtStockGondola.setText("Stock Góndola: " + productoActual.getStockGondola());
    }
}
