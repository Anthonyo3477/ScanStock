package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectoprueba.R;

public class registrarPersonal extends AppCompatActivity {

    private EditText etNombrePersonal, etRut, etDireccion, etCorreo, etRol;
    private Button btnGuardar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_personal);

        etNombrePersonal = findViewById(R.id.etNombrePersonal);
        etRut = findViewById(R.id.etRut);
        etDireccion = findViewById(R.id.etDireccion);
        etCorreo = findViewById(R.id.etCorreo);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);


        // Boton para Guardar

        // Boton para Volver
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
