package com.example.proyectoprueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        ImageButton iconAvisos = findViewById(R.id.iconAvisos);
        Button btnVolver = findViewById(R.id.btnVolver);

        // Ir a Avisos
        iconAvisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Avisos.class);
                startActivity(intent);
                // aquí NO pongo finish() para que se pueda volver atrás
            }
        });

        // Botón volver al Home
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // cierra Menu y regresa a Home
            }
        });
    }
}
