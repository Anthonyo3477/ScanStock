package com.example.proyectoprueba.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registrarPersonal extends AppCompatActivity {

    private EditText etNombrePersonal, etRut, etDireccion, etCorreo, etContraseña, etRol;
    private Button btnGuardar, btnVolver;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_personal);

        // Inputs
        etNombrePersonal = findViewById(R.id.etNombrePersonal);
        etRut = findViewById(R.id.etRut);
        etDireccion = findViewById(R.id.etDireccion);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etRol = findViewById(R.id.etRol);

        // Botones
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);

        // Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnGuardar.setOnClickListener(v -> registrarPersonal());
        btnVolver.setOnClickListener(v -> finish());
    }

    private void registrarPersonal() {

        String nombre = etNombrePersonal.getText().toString().trim();
        String rut = etRut.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();
        String rol = etRol.getText().toString().trim().toLowerCase();

        if (nombre.isEmpty() || rut.isEmpty() || direccion.isEmpty()
                || correo.isEmpty() || contraseña.isEmpty() || rol.isEmpty()) {

            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear usuario en AUTH
        auth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnSuccessListener(authResult -> {

                    String uid = authResult.getUser().getUid();

                    Map<String, Object> usuario = new HashMap<>();
                    usuario.put("nombre", nombre);
                    usuario.put("rut", rut);
                    usuario.put("direccion", direccion);
                    usuario.put("correo", correo);
                    usuario.put("rol", rol);

                    // Guardar en Firestore con el MISMO UID
                    db.collection("usuarios").document(uid)
                            .set(usuario)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(this, "Personal registrado correctamente", Toast.LENGTH_LONG).show();
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Error Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show()
                            );

                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error Auth: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }
}