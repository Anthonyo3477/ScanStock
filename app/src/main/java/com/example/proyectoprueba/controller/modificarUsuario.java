package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class modificarUsuario extends AppCompatActivity {

    private EditText etNombre, etRut, etDireccion, etCorreo, etRol;
    private Button btnActualizar;
    private FirebaseFirestore db;
    private String idUsuario;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_usuario);

        etNombre = findViewById(R.id.etNombre);
        etRut = findViewById(R.id.etRut);
        etDireccion = findViewById(R.id.etDireccion);
        etCorreo = findViewById(R.id.etCorreo);
        etRol = findViewById(R.id.etRol);

        btnActualizar = findViewById(R.id.btnActualizar);
        db = FirebaseFirestore.getInstance();

        idUsuario = getIntent().getStringExtra("idUsuario");

        etNombre.setText(getIntent().getStringExtra("nombre"));
        etRut.setText(getIntent().getStringExtra("rut"));
        etDireccion.setText(getIntent().getStringExtra("direccion"));
        etCorreo.setText(getIntent().getStringExtra("correo"));
        etRol.setText(getIntent().getStringExtra("rol"));

        btnActualizar.setOnClickListener(v -> actualizarUsuario());
    }
    private void actualizarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String rut = etRut.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String rol = etRol.getText().toString().trim();

        if (nombre.isEmpty() || rut.isEmpty() || direccion.isEmpty() || correo.isEmpty() || rol.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> datosActualizados = new HashMap<>();
        datosActualizados.put("nombre", nombre);
        datosActualizados.put("rut", rut);
        datosActualizados.put("direccion", direccion);
        datosActualizados.put("correo", correo);
        datosActualizados.put("rol", rol);

        db.collection("usuarios")
                .document(idUsuario)
                .update(datosActualizados)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });

        }
    }