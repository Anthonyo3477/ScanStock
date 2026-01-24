package com.example.proyectoprueba.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectoprueba.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private EditText etCorreo, etContraseña;
    private Button btnIngresar, btnCerrarSecion;
    private ProgressBar progressLogin;

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
        progressLogin = findViewById(R.id.progress_login);

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

        progressLogin.setVisibility(View.VISIBLE);
        btnIngresar.setEnabled(false);
        btnCerrarSecion.setEnabled(false);

        auth.signInWithEmailAndPassword(correo, contraseña)
                .addOnSuccessListener(authResult -> {

                    db.collection("usuarios")
                            .whereEqualTo("correo", correo)
                            .limit(1)
                            .get()
                            .addOnSuccessListener(querySnapshot -> {

                                if (!querySnapshot.isEmpty()) {

                                    String rol = querySnapshot.getDocuments()
                                            .get(0)
                                            .getString("rol");

                                    if (rol == null) {
                                        mostrarError("Rol no definido");
                                        return;
                                    }

                                    redirigirPorRol(rol);

                                } else {
                                    mostrarError("Usuario no existe en Firestore");
                                }

                            })
                            .addOnFailureListener(e ->
                                    mostrarError("Error Firestore: " + e.getMessage())
                            );

                })
                .addOnFailureListener(e ->
                        mostrarError("Error login: " + e.getMessage())
                );
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

        progressLogin.setVisibility(View.GONE);
        btnIngresar.setEnabled(true);
        btnCerrarSecion.setEnabled(true);
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