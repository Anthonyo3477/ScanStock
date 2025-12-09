package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class menuUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_usuario);

        Button btnAvisoProducto = findViewById(R.id.btnAvisoProducto);
        Button btnAviso = findViewById(R.id.btnAviso);
        Button btnVolver = findViewById(R.id.btnVolver);

        // Ir a Productos

        btnAvisoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menuUsuario.this, AgregarProducto.class);
                startActivity(intent);

            }
        });

        btnAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( menuUsuario.this, Avisos.class);
                startActivity(intent);
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
