package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;

public class agregarAviso extends AppCompatActivity {

    private EditText etNombre, etCategoria, etCodigoBarras, etCantidad, etStockBodega, etStockGondola;
    private Button btnGuardar, btnVolver, btnEscanear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_aviso);

        etNombre = findViewById(R.id.etNombre);
        etCategoria = findViewById(R.id.etCategoria);
        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidad = findViewById(R.id.etCantidad);
        etStockBodega = findViewById(R.id.etStockBodega);
        etStockGondola = findViewById(R.id.etStockGondola);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);
        btnEscanear = findViewById(R.id.btnEscanear);


        // Boton para Guardar

        // Boton para Escanear

        // boton para volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
