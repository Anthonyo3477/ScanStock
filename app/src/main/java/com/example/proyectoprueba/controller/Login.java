package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private EditText etCorreo, etContraseña;
    private Button btnIngresar, btnCerrarSecion;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnCerrarSecion = findViewById(R.id.btnCerrarSecion);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnIngresar.setOnClickListener(v -> loginUsuario());
        btnCerrarSecion.setOnClickListener(v -> finish());
    }

    private void loginUsuario() {

        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString().trim();

        if (correo.isEmpty() || contraseña.isEmpty()) {
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(correo, contraseña)
                .addOnSuccessListener(authResult -> {

                    // Buscar usuario por correo en Firestore
                    db.collection("usuarios").whereEqualTo("correo", correo).limit(1).get() .addOnSuccessListener(querySnapshot -> {
                                if (!querySnapshot.isEmpty()) {
                                    String rol = querySnapshot.getDocuments().get(0).getString("rol");
                                    if (rol == null) {
                                        Toast.makeText(this, "Rol no definido", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                    redirigirPorRol(rol);

                                } else {
                                    Toast.makeText(this, "Usuario no existe en Firestore", Toast.LENGTH_LONG).show();
                                }

                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Error Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show()
                            );

                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Error login: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }

    private void redirigirPorRol(String rol) {

        rol = rol.toLowerCase().trim();
        Intent intent;
        switch (rol) {
            case "superadmin":
                intent = new Intent(this, menuSuperAdmin.class);
                break;

            case "admin":
                intent = new Intent(this, menuAdmin.class);
                break;

            default:
                intent = new Intent(this, menuUsuario.class);
                break;
        }

        startActivity(intent);
        finish();
    }
}