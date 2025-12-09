package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registrar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);

        Button btnIngresar = findViewById(R.id.btnIngresar);
        Button btnCerrarSecion = findViewById(R.id.btnCerrarSecion);
        Button btnIngresarUsuario = findViewById(R.id.btnIngresarUsuario);
        Button btnIngresarSuperAdmin = findViewById(R.id.btnIngresarSuperAdmin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this, Menu.class);
                startActivity(intent);

            }
        });

        btnIngresarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this, menuUsuario.class);
                startActivity(intent);
            }
        });

        btnIngresarSuperAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar.this, Menu.class);
                startActivity(intent);
            }
        });

        btnCerrarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
