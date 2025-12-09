package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menuSuperAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_super_admin);

        Button btnGestionUsuarios = findViewById(R.id.btnGestionUsuarios);
        Button btnVolver = findViewById(R.id.btnVolver);

        // Ir a Gestion de Usuarios

        btnGestionUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // crear la ventana de Gestions de usuarios, esta ventana listara todos los usuarios,
                // al momento de precionar un boton para modificar podremos cambiar de rol de los usuarios
                //Intent intent = new Intent(menuSuperAdmin.this, GestionUsuarios.class);
                //startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
