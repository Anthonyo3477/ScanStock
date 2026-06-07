package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class reponerProducto extends AppCompatActivity {

    private EditText etCodigoBarras;
    private EditText etCantidadRepuesta;

    private RadioGroup radioGroupDestino;
    private RadioButton rbBodega;
    private RadioButton rbGondola;

    private Button btnConfirmar;
    private Button btnVolver;

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reponer_producto);

        db = FirebaseFirestore.getInstance();

        etCodigoBarras = findViewById(R.id.etCodigoBarras);
        etCantidadRepuesta = findViewById(R.id.etCantidadRepuesta);

        radioGroupDestino = findViewById(R.id.radioGroupDestino);

        rbBodega = findViewById(R.id.rbBodega);
        rbGondola = findViewById(R.id.rbGondola);

        btnConfirmar = findViewById(R.id.btnConfirmar);
        btnVolver = findViewById(R.id.btnVolver);

        btnConfirmar.setOnClickListener(v -> validarDatos());

        btnVolver.setOnClickListener(v -> finish());
    }

    private void validarDatos() {
        String codigo = etCodigoBarras.getText().toString().trim();
        String cantidadTexto = etCantidadRepuesta.getText().toString().trim();

        if (codigo.isEmpty()) {etCodigoBarras.setError("Ingrese un código");
            return;
        }

        if (cantidadTexto.isEmpty()) {etCantidadRepuesta.setError("Ingrese una cantidad");
            return;
        }

        if (!rbBodega.isChecked() && !rbGondola.isChecked()) {
            Toast.makeText(this, "Seleccione Bodega o Góndola", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Datos validados correctamente", Toast.LENGTH_SHORT).show();
    }
}